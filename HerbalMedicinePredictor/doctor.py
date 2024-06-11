from public import *
import uuid


doctor=Blueprint('doctor',__name__)

@doctor.route('/doctor_home')
def doctor_home():
	return render_template('doctor_home.html')

@doctor.route('/doctor_view_profile',methods=['get','post'])
def doctor_view_profile():
	data={}
	did=session['did']
	q="select * from doctor where doctor_id='%s'"%(did)
	res=select(q)
	if res:
		data['dir']=res
		print(res)

	if 'update' in request.form:
		doc=request.form['doc']
		pl=request.form['pl']
		ph=request.form['ph']
		em=request.form['em']
		q="update doctor set doctor='%s',place='%s',phone='%s',email='%s' where doctor_id='%s'"%(doc,pl,ph,em,did)
		update(q)
		flash("updated")
		return redirect(url_for('doctor.doctor_view_profile'))
	return render_template('doctor_view_profile.html',data=data)


@doctor.route('/doctor_view_bookings')
def doctor_view_bookings():
	data={}
	did=session['did']
	q="SELECT * FROM `booking` INNER JOIN USER USING(user_id) where doctor_id='%s'"%(did)
	res=select(q)
	data['book']=res

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='accept':
		q="update booking set status='Accepted' where booking_id='%s'"%(id)
		update(q)
		flash("Accepted")
		return redirect(url_for('doctor.doctor_view_bookings'))

	if action=='reject':
		q="update booking set status='Rejected' where booking_id='%s'"%(id)
		update(q)
		flash("Rejected")
		return redirect(url_for('doctor.doctor_view_bookings'))
	return render_template('doctor_view_bookings.html',data=data)


@doctor.route('/doctor_add_schedule',methods=['get','post'])
def doctor_add_schedule():
	data={}
	did=session['did']
	if 'manage' in request.form:
		date=request.form['date']
		time=request.form['time']
		q="insert into schedule values(NULL,'%s','%s','%s')"%(did,date,time)
		insert(q)
		flash("Added Successfully...!")
		return redirect(url_for('doctor.doctor_add_schedule'))

	q="select * from schedule where doctor_id='%s'"%(did)
	res=select(q)
	if res:
		data['sh']=res
		print(res)

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='delete':
		q="delete from schedule where schedule_id='%s'"%(id)
		delete(q)
		flash("deleted.....!")
		return redirect(url_for('doctor.doctor_add_schedule'))

	if action=='update':
		q="select * from schedule where schedule_id='%s'"%(id)
		data['dir']=select(q)

	if 'update' in request.form:
		date=request.form['date']
		time=request.form['time']
		q="update schedule set date='%s',time='%s' where schedule_id='%s'"%(date,time,id)
		update(q)
		flash("updated")
		return redirect(url_for('doctor.doctor_add_schedule'))
	return render_template("doctor_add_schedule.html",data=data)