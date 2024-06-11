from public import *
import uuid


sales=Blueprint('sales',__name__)

@sales.route('/sales_home')
def sales_home():
	return render_template('sales_home.html')


@sales.route('/sales_add_medicines',methods=['get','post'])
def sales_add_medicines():
	data={}
	sid=session['sid']

	if 'manage' in request.form:
		med=request.form['med']
		qua=request.form['qua']
		amt=request.form['amt']
		q="insert into medicine values(NULL,'%s','%s','%s','%s',curdate())"%(sid,med,qua,amt)
		insert(q)
		flash("Added Successfully...!")
		return redirect(url_for('sales.sales_add_medicines'))

	q="select * from medicine where sales_id='%s'"%(sid)
	res=select(q)
	if res:
		data['med']=res
		print(res)

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='delete':
		q="delete from medicine where medicine_id='%s'"%(id)
		delete(q)
		flash("deleted.....!")
		return redirect(url_for('sales.sales_add_medicines'))

	if action=='update':
		q="select * from medicine where medicine_id='%s'"%(id)
		data['dir']=select(q)

	if 'update' in request.form:
		med=request.form['med']
		qua=request.form['qua']
		amt=request.form['amt']
		q="update medicine set medicine_name='%s',quantity='%s',amount='%s' where medicine_id='%s'"%(med,qua,amt,id)
		update(q)
		flash("updated")
		return redirect(url_for('sales.sales_add_medicines'))
	return render_template("sales_add_medicines.html",data=data)


@sales.route('/sales_add_plants_for_sale',methods=['get','post'])
def sales_add_plants_for_sale():
	data={}
	sid=session['sid']

	if 'manage' in request.form:
		pl=request.form['pl']
		qua=request.form['qua']
		amt=request.form['amt']
		q="insert into plant_for_sale values(NULL,'%s','%s','%s')"%(pl,qua,amt)
		insert(q)
		flash("Added Successfully...!")
		return redirect(url_for('sales.sales_add_plants_for_sale'))

	q="select * from plant_for_sale"
	res=select(q)
	if res:
		data['plant']=res
		print(res)

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='delete':
		q="delete from plant_for_sale where plantsale_id='%s'"%(id)
		delete(q)
		flash("deleted.....!")
		return redirect(url_for('sales.sales_add_plants_for_sale'))

	if action=='update':
		q="select * from plant_for_sale where plantsale_id='%s'"%(id)
		data['dir']=select(q)

	if 'update' in request.form:
		pl=request.form['pl']
		qua=request.form['qua']
		amt=request.form['amt']
		q="update plant_for_sale set plant='%s',quantity='%s',amount='%s' where plantsale_id='%s'"%(pl,qua,amt,id)
		update(q)
		flash("updated")
		return redirect(url_for('sales.sales_add_plants_for_sale'))
	return render_template("sales_add_plants_for_sale.html",data=data)


@sales.route('/sales_view_orders')
def sales_view_orders():
	data={}
	sid=session['sid']
	q="SELECT * FROM `order` INNER JOIN `user` USING(user_id) where sales_id='%s'"%(sid)
	res=select(q)
	data['order']=res
	return render_template('sales_view_orders.html',data=data)


@sales.route('/sales_view_medicine')
def sales_view_medicine():
	data={}
	sid=session['sid']
	id=request.args['id']
	q="SELECT *,`order_detail`.`quantity` AS qua,`order_detail`.`amount` AS amt FROM `order_detail` INNER JOIN `medicine` USING(medicine_id) where order_id='%s'"%(id)
	res=select(q)
	data['order']=res
	return render_template('sales_view_medicine.html',data=data)


@sales.route('/sales_view_payments')
def sales_view_payments():
	data={}
	id=request.args['id']
	q="SELECT * FROM `payment` WHERE payed_id='%s' AND payed_for='medicine'"%(id)
	res=select(q)
	data['pay']=res
	return render_template('sales_view_payments.html',data=data)


@sales.route('/sales_view_purchase')
def sales_view_purchase():
	data={}
	q="SELECT * FROM `purchase` INNER JOIN `user` USING(user_id)"
	res=select(q)
	data['pur']=res
	return render_template('sales_view_purchase.html',data=data)


@sales.route('/sales_view_purchase_payments')
def sales_view_purchase_payments():
	data={}
	id=request.args['id']
	q="SELECT * FROM `payment` WHERE payed_id='%s' AND payed_for='plants'"%(id)
	res=select(q)
	data['pay']=res
	return render_template('sales_view_purchase_payments.html',data=data)