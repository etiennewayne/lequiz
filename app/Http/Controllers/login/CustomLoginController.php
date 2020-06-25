<?php

namespace App\Http\Controllers\login;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;

class CustomLoginController extends Controller
{
    //


    public function showForm(){
    	return view('login/custom-login');

    }
}
