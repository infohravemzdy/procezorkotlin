package org.hravemzdy.procezor.registry.factories

import org.hravemzdy.legalios.interfaces.IPeriod
import org.hravemzdy.procezor.interfaces.ISpecCode
import org.hravemzdy.procezor.interfaces.ISpecDefine
import org.hravemzdy.procezor.registry.providers.ISpecProvider
import org.hravemzdy.procezor.service.types.VersionCode
import java.lang.reflect.Modifier
import org.reflections.Reflections

typealias CODE = Int

interface ISpecFactory<P : ISpecProvider<S, C>, S : ISpecDefine<C>, C : ISpecCode>  {
    fun getSpec(code: C, period: IPeriod, version: VersionCode): S
    fun getSpecList(period: IPeriod, version: VersionCode): Iterable<S>
}

abstract class SpecFactory<P : ISpecProvider<S, C>, S : ISpecDefine<C>, C : ISpecCode>() : ISpecFactory<P, S, C>
{
    protected abstract val providers: Map<CODE, P>
    protected abstract val notFoundProvider: P
    protected abstract val notFoundSpec: S

    override fun getSpec(code: C, period: IPeriod, version: VersionCode): S  {
        val provider: P? = getProvider(code, notFoundProvider)
        if (provider == null) {
            return notFoundSpec
        }
        return provider.getSpec(period, version)
    }
    override fun getSpecList(period: IPeriod, version: VersionCode): Iterable<S> {
        return providers.map {x -> x.value.getSpec(period, version) }.toList()
    }
    private fun getProvider(code: C, defProvider: P): P {
        val provider = providers.getOrDefault(code.value, defProvider)

        return provider
    }
    companion object {
        inline fun <reified P : ISpecProvider<S, C>, S : ISpecDefine<C>, C : ISpecCode> buildProvidersFromAssembly(namespace: String): Map<CODE, P> {
            val providerType: Class<out P> = P::class.java

            val reflections = Reflections(namespace)

            val definedTypes = reflections.getSubTypesOf(providerType)

            val providers = definedTypes
                .filter { x -> isValidType<P, S, C>(x) && hasValidConstructor<P, S, C>(x) }
                .mapNotNull { x -> x.getDeclaredConstructor().newInstance() }.associateBy { it.code.value }
            return providers
        }

        inline fun <reified P : ISpecProvider<S, C>, S : ISpecDefine<C>, C : ISpecCode> isValidType(testType: Class<out P>): Boolean {
            val providerType: Class<out P> = P::class.java

            return (providerType.isAssignableFrom(testType) &&
                    !testType.isInterface && !Modifier.isAbstract(testType.modifiers));
        }

        inline fun <reified P : ISpecProvider<S, C>, S : ISpecDefine<C>, C : ISpecCode> hasValidConstructor(testType: Class<out P>): Boolean
        {
            val parameterlessConstructor = testType.constructors
                .singleOrNull { constructor ->  constructor.parameterCount == 0}
            return parameterlessConstructor != null
        }
    }
}
