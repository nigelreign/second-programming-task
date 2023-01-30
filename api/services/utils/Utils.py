
from random import randint
import pyotp
from datetime import datetime
from django.conf import settings
import json
from datetime import date

with open("config.json") as config_file:
    config = json.load(config_file)


class Utils:
    def get_messages():
        return {
            "non_or_empty": ["", None],
            "401": "Unauthorized",
            "400_Missing_Headers": "Missing api key or username",
            "invalid_data": "Invalid data format",
            "success": "Success",
            "403_Invalid_Transaction": "Invalid transaction type",
            "400_Exception": "Transaction couldn't be processed",
            "400_Missing_Number": "Phone number not supplied",
            "invalid_dates": "Invalid date formats, format is YYYY-mm-dd",
            "invalid_transaction_type": "Invalid Transaction Type",
            "inactive_merchant": "Your merchant profile is inactive",
            "403_Duplicate_Reference": "Duplicate merchant reference",
            "400_Missing_Reg_Number": "Student registration number not supplied",
            "400_Invalid_BillerId": "Invalid biller id",
            "connection_error": "Connection error",
        }
