from public import *
import uuid


admin=Blueprint('admin',__name__)

@admin.route('/admin_home')
def admin_home():
	return render_template('admin_home.html')


@admin.route('/admin_manage_researchers',methods=['get','post'])
def admin_manage_researchers():
	data={}
	if 'manage' in request.form:
		fn=request.form['fn']
		ln=request.form['ln']
		pl=request.form['pl']
		ph=request.form['ph']
		em=request.form['em']
		un=request.form['un']
		ps=request.form['pass']
		q="insert into login values(NULL,'%s','%s','Researcher')"%(un,ps)
		id=insert(q)
		q="insert into research values(NULL,'%s','%s','%s','%s','%s','%s')"%(id,fn,ln,pl,ph,em)
		insert(q)
		flash(" Researcher Registered Successfully...!")
		return redirect(url_for('admin.admin_manage_researchers'))

	q="select * from research"
	res=select(q)
	if res:
		data['research']=res
		print(res)

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='delete':
		q="delete from research where login_id='%s'"%(id)
		delete(q)
		q="delete from login where login_id='%s'"%(id)
		delete(q)
		flash("deleted.....!")
		return redirect(url_for('admin.admin_manage_researchers'))

	if action=='update':
		q="select * from research where login_id='%s'"%(id)
		data['dir']=select(q)

	if 'update' in request.form:
		fn=request.form['fn']
		ln=request.form['ln']
		pl=request.form['pl']
		ph=request.form['ph']
		em=request.form['em']
		q="update research set first_name='%s',last_name='%s',place='%s',phone='%s',email='%s' where login_id='%s'"%(fn,ln,pl,ph,em,id)
		update(q)
		flash("updated")
		return redirect(url_for('admin.admin_manage_researchers'))
	return render_template("admin_manage_researchers.html",data=data)


@admin.route('/admin_manage_doctor',methods=['get','post'])
def admin_manage_doctor():
	data={}
	if 'manage' in request.form:
		doc=request.form['doc']
		pl=request.form['pl']
		ph=request.form['ph']
		em=request.form['em']
		un=request.form['un']
		ps=request.form['pass']
		q="insert into login values(NULL,'%s','%s','doctor')"%(un,ps)
		id=insert(q)
		q="insert into doctor values(NULL,'%s','%s','%s','%s','%s')"%(id,doc,pl,ph,em)
		insert(q)
		flash("Registered Successfully...!")
		return redirect(url_for('admin.admin_manage_doctor'))

	q="select * from doctor"
	res=select(q)
	if res:
		data['doc']=res
		print(res)

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='delete':
		q="delete from doctor where login_id='%s'"%(id)
		delete(q)
		q="delete from login where login_id='%s'"%(id)
		delete(q)
		flash("deleted.....!")
		return redirect(url_for('admin.admin_manage_doctor'))

	if action=='update':
		q="select * from doctor where login_id='%s'"%(id)
		data['dir']=select(q)

	if 'update' in request.form:
		doc=request.form['doc']
		pl=request.form['pl']
		ph=request.form['ph']
		em=request.form['em']
		q="update doctor set doctor='%s',place='%s',phone='%s',email='%s' where login_id='%s'"%(doc,pl,ph,em,id)
		update(q)
		flash("updated")
		return redirect(url_for('admin.admin_manage_doctor'))
	return render_template("admin_manage_doctor.html",data=data)


@admin.route('/admin_manage_sales_manager',methods=['get','post'])
def admin_manage_sales_manager():
	data={}
	if 'manage' in request.form:
		fn=request.form['fn']
		ln=request.form['ln']
		pl=request.form['pl']
		ph=request.form['ph']
		em=request.form['em']
		un=request.form['un']
		ps=request.form['pass']
		q="insert into login values(NULL,'%s','%s','Sales Manager')"%(un,ps)
		id=insert(q)
		q="insert into sales values(NULL,'%s','%s','%s','%s','%s','%s')"%(id,fn,ln,pl,ph,em)
		insert(q)
		flash("Sales Manager Registered Successfully...!")
		return redirect(url_for('admin.admin_manage_sales_manager'))

	q="select * from sales"
	res=select(q)
	if res:
		data['sales']=res
		print(res)

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='delete':
		q="delete from sales where login_id='%s'"%(id)
		delete(q)
		q="delete from login where login_id='%s'"%(id)
		delete(q)
		flash("deleted.....!")
		return redirect(url_for('admin.admin_manage_sales_manager'))

	if action=='update':
		q="select * from sales where login_id='%s'"%(id)
		data['dir']=select(q)

	if 'update' in request.form:
		fn=request.form['fn']
		ln=request.form['ln']
		pl=request.form['pl']
		ph=request.form['ph']
		em=request.form['em']
		q="update sales set first_name='%s',last_name='%s',place='%s',phone='%s',email='%s' where login_id='%s'"%(fn,ln,pl,ph,em,id)
		update(q)
		flash("updated")
		return redirect(url_for('admin.admin_manage_sales_manager'))
	return render_template("admin_manage_sales_manager.html",data=data)


@admin.route('/admin_view_feedback')
def admin_view_feedback():
	data={}
	q="SELECT * FROM `feedback` inner join user using(user_id)"
	res=select(q)
	data['feedback']=res
	return render_template("admin_view_feedback.html",data=data)

@admin.route('/admin_view_materials')
def admin_view_materials():
	data={}
	q="SELECT * FROM `materials` INNER JOIN `research` USING(`researcher_id`)"
	res=select(q)
	data['mat']=res

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='accept':
		q="update materials set status='Accepted' where material_id='%s'"%(id)
		update(q)
		flash("Accepted")
		return redirect(url_for('admin.admin_view_materials'))

	if action=='reject':
		q="update materials set status='Rejected' where material_id='%s'"%(id)
		update(q)
		flash("Rejected")
		return redirect(url_for('admin.admin_view_materials'))
	return render_template("admin_view_materials.html",data=data)


@admin.route('/admin_view_medical_plants')
def admin_view_medical_plants():
	data={}
	q="SELECT * FROM `medicalplant` INNER JOIN `research` USING(`researcher_id`)"
	res=select(q)
	data['plants']=res
	return render_template("admin_view_medical_plants.html",data=data)


@admin.route('/admin_view_cultivation_technique',methods=['get','post'])
def admin_view_cultivation_technique():
	data={}
	q="SELECT * FROM `cultitechnique`"
	res=select(q)
	data['tech']=res
	
	if "submit" in request.form:
		plant=request.form['plant']
		details=request.form['details']
		q="insert into cultitechnique values(null,'%s','%s')"%(plant,details)
		insert(q)
		return render_template("admin_view_cultivation_technique.html",data=data)


	
	return render_template("admin_view_cultivation_technique.html",data=data)


@admin.route('/admin_view')
def admin_view():
	data={}
	q="SELECT * FROM `uploadvideo`"
	res=select(q)
	data['tech']=res
	return render_template("admin_view.html",data=data)