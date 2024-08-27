package data.utils

interface Mapper<in Input, out Output> {
    operator fun invoke(input: Input): Output
}