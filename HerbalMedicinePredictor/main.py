from flask import Flask 
from public import public
from admin import admin
from researcher import researcher
from sales import sales
from doctor import doctor
from api import api

app=Flask(__name__)
app.secret_key="key"

app.register_blueprint(public)
app.register_blueprint(admin,url_prefix='/admin')
app.register_blueprint(researcher,url_prefix='/researcher')
app.register_blueprint(sales,url_prefix='/sales')
app.register_blueprint(doctor,url_prefix='/doctor')
app.register_blueprint(api,url_prefix='/api')

app.run(debug=True, host = "0.0.0.0",port=5689)