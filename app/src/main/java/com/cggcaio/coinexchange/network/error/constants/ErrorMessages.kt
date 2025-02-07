package com.cggcaio.coinexchange.network.error.constants

object ErrorMessages {
    const val BAD_REQUEST = "Parece que há algo errado com sua solicitação. Por favor, verifique e tente novamente."
    const val UNAUTHORIZED = "Acesso não autorizado. Verifique sua chave de acesso e tente novamente."
    const val FORBIDDEN =
        "Você não tem permissão para acessar este recurso. Entre em contato com o suporte se precisar de ajuda."
    const val TOO_MANY_REQUESTS = "Muitas solicitações em um curto período. Aguarde um momento e tente novamente."
    const val NO_DATA = "O item solicitado não está disponível no momento. Tente novamente mais tarde."
    const val GENERIC_ERROR = "Ocorreu um erro ao processar sua solicitação. Por favor, tente novamente mais tarde."
}
