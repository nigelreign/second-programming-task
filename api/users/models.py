import datetime
import uuid

from django.contrib.auth.models import AbstractUser
from django.db import models


class User(AbstractUser):
    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['username', 'first_name', 'last_name']
    email = models.EmailField(unique=True)
    first_name = models.CharField(max_length=255)
    password = models.CharField(max_length=255)
    last_name = models.CharField(max_length=255)

    class Meta:
        verbose_name = "Admin User"
        verbose_name_plural = "Admin Users"
        ordering = ["username"]

    def __str__(self):
        return self.email
    
    
class Customer(models.Model):
    name = models.CharField(max_length=255)
    username = models.CharField(max_length=255)
    email = models.EmailField(max_length=255)
    address = models.JSONField()
    phone = models.CharField(max_length=255)
    website = models.CharField(max_length=255)
    company = models.JSONField()
    
    def __str__(self):
        return self.name



