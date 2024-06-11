from flask import *
from database import *
import uuid

public=Blueprint('public',__name__)

@public.route('/')
def home():
	return render_template('home.html')

@public.route('/login',methods=['get','post'])
def login():

	if 'submit' in request.form:
		uname=request.form['uname']
		pwd=request.form['pwd']
		q="SELECT * FROM `login` WHERE `username`='%s' AND `password`='%s'"%(uname,pwd)
		res=select(q)
		if res:
			session['lid']=res[0]['login_id']
			if res[0]['usertype']=='admin':
				flash("login successfully....!")
				return redirect(url_for('admin.admin_home'))

			elif res[0]['usertype']=='Researcher':
				q="select * from research where login_id='%s'"%(session['lid'])
				res=select(q)
				session['rid']=res[0]['researcher_id']
				flash("Researcher login successfully....!")
				return redirect(url_for('researcher.researcher_home'))

			elif res[0]['usertype']=='Sales Manager':
				q="select * from sales where login_id='%s'"%(session['lid'])
				res=select(q)
				session['sid']=res[0]['sales_id']
				flash("Sales Manager login successfully....!")
				return redirect(url_for('sales.sales_home'))

			elif res[0]['usertype']=='doctor':
				q="select * from doctor where login_id='%s'"%(session['lid'])
				res=select(q)
				session['did']=res[0]['doctor_id']
				flash("login successfully....!")
				return redirect(url_for('doctor.doctor_home'))
				
			elif res[0]['usertype']=='user':
				q="select * from user where login_id='%s'"%(session['lid'])
				res=select(q)
				session['did']=res[0]['doctor_id']
				flash("login successfully....!")
				return redirect(url_for('doctor.doctor_home'))
		else:
			flash("INVALID USERNAME OR PASSWORD")
	return render_template('login.html')


