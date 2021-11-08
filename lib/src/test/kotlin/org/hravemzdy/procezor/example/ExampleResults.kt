package org.hravemzdy.procezor.example

import org.hravemzdy.procezor.interfaces.ITermTarget

class ExampleResultConst {
    companion object {
        val VALUE_ZERO: Int = 0
        val BASIS_ZERO: Int = 0
        val DESCRIPTION_EMPTY: String = "result from input value"
    }
}

class TimeshtWorkingResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class AmountBasisResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class AmountFixedResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class HealthInsbaseResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class SocialInsbaseResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class HealthInspaymResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class SocialInspaymResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class TaxingAdvbaseResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class TaxingAdvpaymResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class IncomeGrossResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

class IncomeNettoResult : ExampleTermResult {
    constructor(target: ITermTarget, value: Int, basis: Int, descr: String) : super(target, basis, value, descr)

    constructor(target: ITermTarget) : this(target, ExampleResultConst.VALUE_ZERO, ExampleResultConst.BASIS_ZERO, ExampleResultConst.DESCRIPTION_EMPTY)
}

