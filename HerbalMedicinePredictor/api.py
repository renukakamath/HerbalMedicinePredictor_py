from flask import *
from database import *
import demjson


import uuid

from newcnn import *

api=Blueprint('api',__name__)


@api.route('/login')
def login():
	data={}
	username=request.args['username']
	password=request.args['password']
	q="select * from login where username='%s' and password='%s'"%(username,password)
	r=select(q)
	if r:
		data['status']="success"
		data['data']=r
	else:
		data['status']="failed"
	return demjson.encode(data)

@api.route('/userregister')
def userregister():
	data={}
	f=request.args['fname']
	l=request.args['lname']
	pl=request.args['place']
	ph=request.args['phone']
	e=request.args['email']
	username=request.args['username']
	password=request.args['password']
	q="insert into login values(null,'%s','%s','user')"%(username,password)
	r=insert(q)
	q="insert into user values(null,'%s','%s','%s','%s','%s','%s')"%(r,f,l,e,ph,pl)
	r=insert(q)
	data['status']="success"
	return demjson.encode(data)

@api.route('/Viewresearch')
def Viewresearch():
	data={}
	q="select * from research"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewresearch"
	return demjson.encode(data)

@api.route('/Viewplants')
def Viewplants():
	data={}
	q="select * from plant_for_sale"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewplants"
	return demjson.encode(data)

@api.route('/Serachplants')
def Serachplants():
	data={}
	searched=request.args['serached']+"%"
	q="select * from plant_for_sale where plant like '%s'" %(searched)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewplants"
	return demjson.encode(data)

@api.route('/Viewcultivation')
def Viewcultivation():
	data={}
	q="select * from cultitechnique"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewcultivation"
	return demjson.encode(data)


@api.route('/Viewdoctors')
def Viewdoctors():
	data={}
	q="select * from doctor"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewdoctors"
	return demjson.encode(data)

@api.route('/Serachdoctors')
def Serachdoctors():
	data={}
	searched=request.args['serached']+"%"
	q="select * from doctor where doctor like '%s'" %(searched)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewdoctors"
	return demjson.encode(data)

@api.route('/view_my_feedback')
def view_my_feedback():
	data={}
	login_id=request.args['login_id']
	q="select * from feedback where user_id=(select user_id from user where login_id='%s')" %(login_id)
	res=select(q)
	print(res)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="view_my_feedback"
	return demjson.encode(data)

@api.route('/send_feedback')
def send_feedback():
	data={}
	login_id=request.args['login_id']
	feedback=request.args['feedback']
	
	q="insert into feedback values(null,(select user_id from user where login_id='%s'),'%s',curdate())"%(login_id,feedback)
	r=insert(q)
	data['status']="success"
	data['method']="send_feedback"
	return demjson.encode(data)


@api.route('/Viewmedicine')
def Viewmedicine():
	data={}
	q="select * from medicine"
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewmedicine"
	return demjson.encode(data)

@api.route('/bookdoctor')
def bookdoctor():
	data={}
	lid=request.args['lid']
	did=request.args['did']
	
	q="insert into booking values(null,'%s',(select user_id from user where login_id='%s'),curdate(),'pending')"%(did,lid)
	r=insert(q)
	data['status']="success"
	data['method']="bookdoctor"
	return demjson.encode(data)

@api.route('/Viewbooking')
def Viewbooking():
	data={}
	lid=request.args['lid']
	q="select * from booking inner join doctor using(doctor_id) where user_id=(select user_id from user where login_id='%s')" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewbooking"
	return demjson.encode(data)

@api.route('/purchaseplant')
def purchaseplant():
	data={}
	lid=request.args['lid']
	pid=request.args['pid']
	qty=request.args['qty']
	amount=request.args['amount']
	
	q="insert into purchase values(null,'%s',(select user_id from user where login_id='%s'),'%s','%s',curdate(),'pending')"%(pid,lid,qty,amount)
	r=insert(q)
	data['status']="success"
	data['method']="purchaseplant"
	return demjson.encode(data)

@api.route('/Viewpurchase')
def Viewpurchase():
	data={}
	lid=request.args['lid']
	q="select *,purchase.quantity as quantitys,purchase.amount as amounts from purchase inner join plant_for_sale using(plantsale_id) where user_id=(select user_id from user where login_id='%s')" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewpurchase"
	return demjson.encode(data)


@api.route('/medicineaddtocart')
def medicineaddtocart():
	data={}
	lid=request.args['lid']
	mid=request.args['mid']
	qty=request.args['qty']
	amount=request.args['amount']
	sid=request.args['sid']
	q="select * from `order` where user_id=(select user_id from user where login_id='%s') and status='pending'" %(lid)
	res=select(q)
	if res:
		id=res[0]['order_id']
	else:
		q="insert into `order` values(null,(select user_id from user where login_id='%s'),'%s','0',curdate(),'pending')"%(lid,sid)
		id=insert(q)
	q="select * from order_detail where order_id='%s' and medicine_id='%s'" %(id,mid)
	res1=select(q)
	if res1:
		q="update order_detail set quantity=quantity+'%s' , amount=amount+'%s' where order_id='%s' and medicine_id='%s'" %(qty,amount,id,mid)
		print(q)
		update(q)
	else:
		q="insert into order_detail values(null,'%s','%s','%s','%s')" %(id,mid,qty,amount)
		insert(q)
	q="update `order` set amount=amount+'%s' where order_id='%s'" %(amount,id)
	update(q)

	data['status']="success"
	data['method']="medicineaddtocart"
	return demjson.encode(data)



@api.route('/Viewcart')
def Viewcart():
	data={}
	lid=request.args['lid']
	q="select *,`order_detail`.quantity as quantitys,`order_detail`.amount as amounts from `order` inner join order_detail using(order_id) inner join medicine using(medicine_id) where user_id=(select user_id from user where login_id='%s') and status='pending'" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Viewcart"
	return demjson.encode(data)

@api.route('/Buymedicine')
def Buymedicine():
	data={}
	lid=request.args['lid']
	q="select *,`order_detail`.quantity as quantitys,`order_detail`.amount as amounts from `order` inner join order_detail using(order_id) inner join medicine using(medicine_id) where user_id=(select user_id from user where login_id='%s') and status='pending'" %(lid)
	res=select(q)
	if res:
		q="update `order` set status='Buy' where user_id=(select user_id from user where login_id='%s') and status='pending'" %(lid)
		update(q)
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Buymedicine"
	return demjson.encode(data)


@api.route('/Vieworders')
def Vieworders():
	data={}
	lid=request.args['lid']
	q="select * from `order` where user_id=(select user_id from user where login_id='%s') and status !='pending'" %(lid)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="Vieworders"
	return demjson.encode(data)


@api.route('/chatdetail')
def chatdetail():
	data={}
	sender_id=request.args['sender_id']
	receiver_id=request.args['receiver_id']

	q="select * from chat where (sender_id='%s' and receiver_id='%s') or (sender_id='%s' and receiver_id='%s') " %(sender_id,receiver_id,receiver_id,sender_id)
	res=select(q)
	if res:
		data['status']="success"
		data['data']=res
	else:
		data['status']="failed"
	data['method']="chatdetail"
	return demjson.encode(data)


@api.route('/chat')
def chat():
	data={}
	sender_id=request.args['sender_id']
	receiver_id=request.args['receiver_id']
	details=request.args['details']
	
	q="insert into chat values(null,'%s','%s','%s',curdate())"%(sender_id,receiver_id,details)
	r=insert(q)
	data['status']="success"
	data['method']="chat"
	return demjson.encode(data)


@api.route('/prediction',methods=['get','post'])
def prediction():
	data={}
	image=request.files['image']
	path="static/image"+str(uuid.uuid4())+image.filename
	image.save(path)
	login_id=request.form['login_id']


	print("Starting Application...")
	# from newpredict import predict 	
	outs=predictcnn(path)
	print(outs)
	val=""
	if outs == 0:
		val= "Asthma Plant"
	elif outs == 1:
		val = "Avaram"
	elif outs==2:
		val = "Balloon vine"
	elif outs== 3:
		val = "Bellyache bush (Green)"
	elif outs== 4:
		val = "Benghal dayflower"
	elif outs== 5:
		val= "Big Caltrops"
	elif outs== 6:
		val = "Black-Honey Shrub"
	elif outs== 7:
		val = "Bristly Wild Grape"
	elif outs== 8:
		val = "Butterfly Pea"
	elif outs== 9:
		val = "Cape Gooseberry"


	q="insert into uploadvideo values(null,'%s','%s',curdate())"%(path,val)
	id=insert(q)
	data['status']="success"
	data['method']="prediction"   #change name 
	return demjson.encode(data)




######copy



@api.route('/viewprediction',methods=['get','post'])
def viewprediction():
	data={}
	
	login_id=request.args['lid']


	q="select * from uploadvideo"
	res=select(q)
	data['data']=res	
	data['status']="success"
	data['method']="viewprediction"
	return demjson.encode(data)
