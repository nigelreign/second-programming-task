
from django.urls import path,include
from django.views.decorators.csrf import csrf_exempt
from rest_framework import routers
from rest_framework_simplejwt.views import (
    TokenObtainPairView,
    TokenRefreshView,
)
from api.views.users.views import CustomerViewSet, SaveCustomerListView, UserViewSet
router = routers.DefaultRouter()
router.register('user', UserViewSet, basename='user')
router.register('customer', CustomerViewSet, basename='customer')


urlpatterns = [
    # JWT TOKEN URLS
    path('token/fetch/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
    path('token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
    
    path("customerList/", csrf_exempt(SaveCustomerListView.as_view())),
    path('', include(router.urls))
]
