package org.hravemzdy.procezor.service.types

import com.github.michaelbull.result.Result
import org.hravemzdy.procezor.interfaces.ITermResult
import org.hravemzdy.procezor.interfaces.ITermResultError

typealias BuilderResult = Result<ITermResult, ITermResultError>

typealias BuilderResultList = Iterable<BuilderResult>

