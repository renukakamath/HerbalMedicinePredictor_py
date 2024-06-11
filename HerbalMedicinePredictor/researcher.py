from public import *
import uuid


researcher=Blueprint('researcher',__name__)

@researcher.route('/researcher_home')
def researcher_home():
	return render_template('research_home.html')

@researcher.route('/research_view_profile',methods=['get','post'])
def research_view_profile():
	data={}
	rid=session['rid']
	q="SELECT * FROM `research` where researcher_id='%s'"%(rid)
	res=select(q)
	data['dir']=res


	if 'update' in request.form:
		fn=request.form['fn']
		ln=request.form['ln']
		pl=request.form['pl']
		ph=request.form['ph']
		em=request.form['em']
		q="update research set first_name='%s',last_name='%s',place='%s',phone='%s',email='%s' where researcher_id='%s'"%(fn,ln,pl,ph,em,rid)
		update(q)
		flash("updated")
		return redirect(url_for('researcher.research_view_profile'))
	return render_template("research_view_profile.html",data=data)


@researcher.route('/research_add_materials',methods=['get','post'])
def research_add_materials():
	data={}
	rid=session['rid']
	if 'manage' in request.form:
		mat=request.form['mat']
		det=request.form['det']
		q="insert into materials values(NULL,'%s','%s','%s','pending')"%(rid,mat,det)
		insert(q)
		flash("Added Successfully...!")
		return redirect(url_for('researcher.research_add_materials'))

	q="select * from materials where researcher_id='%s'"%(rid)
	res=select(q)
	if res:
		data['mat']=res
		print(res)

	if 'action' in request.args:
		action=request.args['action']
		id=request.args['id']
	else:
		action=None

	if action=='delete':
		q="delete from materials where material_id='%s'"%(id)
		delete(q)
		flash("deleted.....!")
		return redirect(url_for('researcher.research_add_materials'))

	if action=='update':
		q="select * from materials where material_id='%s'"%(id)
		data['dir']=select(q)

	if 'update' in request.form:
		mat=request.form['mat']
		det=request.form['det']
		q="update materials set materials='%s',details='%s' where material_id='%s'"%(mat,det,id)
		update(q)
		flash("updated")
		return redirect(url_for('researcher.research_add_materials'))
	return render_template("research_add_materials.html",data=data)


@researcher.route('/research_add_medical_plants',methods=['get','post'])
def research_add_medical_plants():
	data={}
	rid=session['rid']
	if 'manage' in request.form:
		pl=request.form['pl']
		img=request.files['img']
		path='static/'+str(uuid.uuid4())+img.filename
		img.save(path)
		q="insert into medicalplant values(NULL,'%s','%s','%s')"%(rid,pl,path)
		insert(q)
		flash("Added Successfully...!")
		return redirect(url_for('researcher.research_add_medical_plants'))

	q="select * from medicalplant where researcher_id='%s'"%(rid)
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
		q="delete from medicalplant where plant_id='%s'"%(id)
		delete(q)
		flash("deleted.....!")
		return redirect(url_for('researcher.research_add_medical_plants'))

	if action=='update':
		q="select * from medicalplant where plant_id='%s'"%(id)
		data['dir']=select(q)

	if 'update' in request.form:
		pl=request.form['pl']
		img=request.files['img']
		path='static/'+str(uuid.uuid4())+img.filename
		img.save(path)
		q="update medicalplant set plant='%s',image='%s' where plant_id='%s'"%(pl,path,id)
		update(q)
		flash("updated")
		return redirect(url_for('researcher.research_add_medical_plants'))
	return render_template("research_add_medical_plants.html",data=data)


@researcher.route('/research_view_chatted_user')
def research_view_chatted_user():
	data={}
	lid=session['lid']
	q="SELECT * FROM `user` WHERE `login_id` IN (SELECT IF(sender_id='%s',receiver_id,sender_id) FROM chat WHERE `sender_id`='%s' OR `receiver_id`='%s')"%(lid,lid,lid)
	res=select(q)
	data['user']=res
	print(res)
	return render_template("research_view_chatted_user.html",data=data)


@researcher.route('/researcher_chat_chatted_user',methods=['get','post'])
def researcher_chat_chatted_user():
	data={}
	lid=session['lid']
	uid=request.args['id']
	data['lid']=lid
	if 'send' in request.form:
		msg=request.form['msg']
		q="INSERT INTO `chat` VALUES(NULL,'%s','%s','%s',NOW())"%(lid,uid,msg)
		insert(q)
		return redirect(url_for('researcher.researcher_chat_chatted_user',id=uid))

	q="SELECT * FROM `chat` where (sender_id='%s' and receiver_id='%s') or (sender_id='%s' and receiver_id='%s')" %(lid,uid,uid,lid)
	res=select(q)
	data['chat']=res
	return render_template("researcher_chat_chatted_user.html",data=data)


@researcher.route('/researcher_view_questions')
def researcher_view_questions():
	data={}
	rid=session['rid']
	q="SELECT * FROM `question_answers` INNER JOIN `user` USING(user_id) where research_id='%s'"%(rid)
	res=select(q)
	data['ans']=res
	return render_template('researcher_view_questions.html',data=data)


@researcher.route('/researcher_send_answer',methods=['get','post'])
def researcher_send_answer():
	data={}
	id=request.args['id']
	if 'send' in request.form:
		msg=request.form['msg']
		q="update `question_answers`set answer='%s' where question_answer_id='%s'"%(msg,id)
		update(q)
		flash("answer added")
		return redirect(url_for('researcher.researcher_view_questions'))
	return render_template("researcher_send_answer.html",data=data)
