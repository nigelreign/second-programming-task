"""
    @author Nigel Zulu
    Email zulunigelb@gmail.com
    Created on 30/6/2021
"""

"""
    GENERIC API RESPONSE
"""


class ApiResponse:
    def __init__(self, successful: bool, message: object):
        self.successful = successful
        self.message = message

    def map(self):
        return {"successful": self.successful, "message": self.message}
