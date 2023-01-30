from rest_framework import serializers
from rest_framework.validators import UniqueValidator
from users.models import Customer, User
import random
from django.contrib.auth.hashers import make_password

class UserSerializer(serializers.ModelSerializer):
    email = serializers.EmailField(
            required=True,
            validators=[UniqueValidator(queryset=User.objects.all(),message="email already exists")]
            )
    username = serializers.CharField(read_only=True)
    password = serializers.CharField(write_only=True)
    class Meta:
        model = User
        fields = ['username','first_name','last_name','email','password']
        
    def create(self, validated_data):
        username = validated_data['first_name'][:1].lower() + validated_data['last_name']
        if User.objects.filter(username=username).exists():
            username = username + str(random.randint(10,99))
        user = User.objects.create(
            username=username,
            first_name=validated_data['first_name'],
            last_name=validated_data['last_name'],
            email=validated_data['email'],
            password=make_password(validated_data['password'])
        )
        return user 
    
class GeoSerializer(serializers.Serializer):
    lat = serializers.CharField(max_length=255)
    lng = serializers.CharField(max_length=255) 
    
    
class AddressSerializer(serializers.Serializer):
    street = serializers.CharField(max_length=255)
    suite = serializers.CharField(max_length=255)
    city = serializers.CharField(max_length=255)
    zipcode = serializers.CharField(max_length=255)
    geo = GeoSerializer(required=True)
    

class CompanySerializer(serializers.Serializer):
    name = serializers.CharField(max_length=255)
    catchPhrase = serializers.CharField(max_length=255)
    bs = serializers.CharField(max_length=255)  
  
class CustomerSerializer(serializers.ModelSerializer):
    name = serializers.CharField(max_length=255)
    username = serializers.CharField(max_length=255)
    email = serializers.EmailField(max_length=255)
    address = AddressSerializer(required=True)
    phone = serializers.CharField(max_length=255)
    website = serializers.CharField(max_length=255)
    company = CompanySerializer(required=True)
    
    class Meta:
        model = Customer
        fields = "__all__"
