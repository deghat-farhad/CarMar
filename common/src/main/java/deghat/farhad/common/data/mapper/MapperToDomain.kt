package deghat.farhad.common.data.mapper

interface MapperToDomain<From, To> {
    fun mapToDomain(from: From): To
}