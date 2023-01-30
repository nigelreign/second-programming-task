## test student number
``` MJ212635 ```

# Stages

## Stage 1
Main Menu

```
position 1 => enter student number
position 2 => confirm student details
position 3 => proceed
```

## Stage 2
School Welcome Menu

```
position 1 => Student Accounts
position 2 => Academic Reports
```

## Stage 3
Accounting

```
position 1 => Pay fees
position 2 => View Balance
position 3 => Download current term invoice
position 4 => Download payment history

```

## Stage 4
Payment options

```
position 1 => Ecocash (MAIN POSITION)
position 2 => Enter number for payements
position 3 => Enter amount
position 4 => Confirm details
position 5 => One Money (MAIN POSITION)
position 6 => Telecash (MAIN POSITION)
position 7 => Master card/ visa (MAIN POSITION)
position 8 => VPayments (MAIN POSITION)
```

## Stage 5

```
position 1 => Download academic report
```

## Other payments options using paynow
https://developers.paynow.co.zw/docs/initiate_transaction.html
https://developers.paynow.co.zw/docs/simple_paynow_request_button.html#step-by-step-example


# ==================================================================================================
# All Permissions
```
fees_module
fees_payment
fees_statements
academic_module
elearning
academic_reports
upload_receipt

```

# ==================================================================================================
# List of schools
# ==================================================================================================

## Providers
documentation: https://console.maytapi.com/
repository: https://github.com/Maytapi/whatsapp-api-python-example/blob/master/app.py

# Sending captioned message
```
 payload = {
            "to_number": self.phone_number,
            "type": "media",
            "filename": "fundo.pdf",
            "text": self.message,
            "message": file_url,
        }

```

# Heroku

https://www.youtube.com/watch?v=Nhl_-1gvQTk
https://github.com/sureshmelvinsigera/Django-Heroku-Deployment-Tutorial/blob/master/README.md

```
push heroku main

heroku run bash -a fundoapp

```
## Tailing logs
```
 tail -f /var/log/syslog
```
