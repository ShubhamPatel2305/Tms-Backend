package com.TmsBackend.Tms.Backend.exceptions

class ExpiredTokenException(message: String) : RuntimeException(message)
class InvalidTokenException(message: String) : RuntimeException(message)
