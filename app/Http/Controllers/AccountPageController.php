<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;

use Validator;
use Illuminate\Support\Facades\Hash;

class AccountPageController extends Controller
{

	public function __construct()
    {
        $this->middleware('auth');
        $this->middleware('admin');
    }


    //

    public function index(){
    	$users = User::all();
    	//return $users;
    	return view('account/account')->with("users", $users);
    }


    public function create(){


    	return view('account/accountcreate');
    }

    public function store(Request $request){

    	$validator = $request->validate([
            'idno' => ['required', 'string', 'max:10', 'unique:users'],
            'username' => ['required', 'string', 'max:20', 'unique:users'],
            'lname' => ['required', 'string', 'max:100'],
            'fname' => ['required', 'string', 'max:100'],
            'email' => ['required', 'string', 'email', 'max:100', 'unique:users'],
            'classification' => ['required', 'string', 'max:100'],
            'password' => ['required', 'string', 'min:1', 'confirmed']
            //'apwd' => ['required', 'string', 'max:100'],

        ]);

       //return $request;

    	$data = User::create([
            'idno' => $request->idno,
            'username' => $request->username,
            'lname' => $request->lname,
            'fname' => $request->fname,
            'mname' => $request->mname,
            'email' => $request->email,
            'classification' => $request->classification,
            'password' => Hash::make($request->password),
            'apwd' => $request->password
        ]);

       //return $data;
		//return $data->id; <-- this will return last insert ID from database
        return redirect('/account')->with('success','Account successfully created.');


    }





    public function edit($id){
    	$user = User::find($id);
    	return view('account/accountedit')->with('user', $user);
    }

    public function update(Request $request, $id){
    //return $request;
        $validator = $request->validate([
            'idno' => ['required', 'string', 'max:10', 'unique:users'],
            'username' => ['required', 'string', 'max:20', 'unique:users'],
            'lname' => ['required', 'string', 'max:100'],
            'fname' => ['required', 'string', 'max:100'],
            'email' => ['required', 'string', 'email', 'max:100', 'unique:users'],
            'classification' => ['required', 'string', 'max:100']
            //'apwd' => ['required', 'string', 'max:100'],

        ]);


        $user = User::find($id);
        $user->idno = $request->idno;
    	$user->username = $request->username;
    	$user->lname = $request->lname;
    	$user->fname = $request->fname;
    	$user->mname = $request->mname;
    	$user->email = $request->email;
        $user->classification = $request->classification;
    	$user->save();

    	return redirect('/account')->with('updated','Account successfully updated.');
    }


    public function destroy($id){
    	User::destroy($id);
    }

    public function data_users(){

        $data = User::all();
        return $data;
    }

    public function reset($id){
        $value = md5(date("Y-m-d H:i:s"));
        $result = substr($value, 0, 5);

        $data = User::find($id);
        $data->password = Hash::make($result);
        $data->save();

        return [['status' => 'success', 'newpwd'=> $result]];
    }

}
