checkfiles = config/
run:
	python3 manage.py runserver 0.0.0.0:8000
 
migrations:
	python3 manage.py makemigrations

migrate:
	python3 manage.py migrate

style:
	black $(checkfiles)

deps:
	python3 -m pip install -r requirements.txt

superuser:
	python3 manage.py createsuperuser

celery:
	celery -A config worker -l info
