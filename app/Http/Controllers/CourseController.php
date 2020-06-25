<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Course;

use Validator;

class CourseController extends Controller
{
    //


	public function __construct(){
		$this->middleware('auth');
	}


	public function index(){
		$courses = Course::all();

		return view('course/course')->with("courses", $courses);
	}


	public function create(){
		
	}

	public function store(Request $request){

	}


}
