package com.ovi.apileveltest.utils

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.nio.charset.Charset
import timber.log.Timber

class CurlLoggingInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val curlCommand = buildCurlCommand(request)
        Timber.tag("okhttp curl").d(curlCommand)

        return chain.proceed(request)
    }

    private fun buildCurlCommand(request: Request): String {
        val curlBuilder = StringBuilder("curl -X ${request.method} \\\n")
        request.headers.forEach { header ->
            curlBuilder.append("  -H \"${header.first}: ${header.second}\" \\\n")
        }
        request.body?.let { body ->
            val contentType = body.contentType()
            if (contentType != null) {
                curlBuilder.append("  -H \"Content-Type: ${contentType}\" \\\n")
            }
            val buffer = okio.Buffer()
            body.writeTo(buffer)
            val charset = contentType?.charset(Charset.forName("UTF-8")) ?: Charset.forName("UTF-8")
            val bodyContent = buffer.readString(charset)
            curlBuilder.append("  --data '${bodyContent}' \\\n")
        }
        curlBuilder.append("  \"${request.url}\"")

        return curlBuilder.toString()
    }
}