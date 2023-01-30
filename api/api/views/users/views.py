
import json

from django.http.response import JsonResponse
from rest_framework.parsers import JSONParser
from rest_framework import status
from rest_framework.renderers import JSONRenderer
from rest_framework.views import APIView

from api.responses.ApiResponse import ApiResponse
from services.utils import Utils
from users.models import Customer, User
from rest_framework.permissions import IsAuthenticated, IsAdminUser
from rest_framework.renderers import JSONRenderer
from rest_framework.parsers import JSONParser
from rest_framework import status, generics
from rest_framework.decorators import action
from rest_framework.views import APIView
from django.http import JsonResponse
from rest_framework import viewsets
from users.models import User
from users.serializer import CustomerSerializer, UserSerializer


class SaveCustomerListView(APIView):
    parser_classes = [JSONParser]
    renderer_classes = [JSONRenderer]

    def post(self, request):
        
        payload = json.loads(request.body)

        # get email and password
        customers = json. loads(payload.get("customers"))
        for customer in customers:
            # save customer
            # save customer to Customer model
            customer_obj = Customer(
                name=customer.get("name"),
                username=customer.get("username"),
                email=customer.get("email"),
                address=customer.get("address"),
                phone=customer.get("phone"),
                website=customer.get("website"),
                company=customer.get("company")
            )
            customer_obj.save()
            
        response = ApiResponse(True, "Success")
        return JsonResponse(status=status.HTTP_200_OK, data=response.map())


class UserViewSet(viewsets.ModelViewSet):
    serializer_class = UserSerializer
    # permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = User.objects.all()
    parser_classes = (JSONParser,)
    http_method_names = ['post', 'get']
    
    def get_queryset(self):
        return User.objects.filter(is_superuser=False)
    
    
class CustomerViewSet(viewsets.ModelViewSet):
    serializer_class = CustomerSerializer
    # permission_classes = [IsAuthenticated, IsAdminUser]
    queryset = Customer.objects.all()
    parser_classes = (JSONParser,)
