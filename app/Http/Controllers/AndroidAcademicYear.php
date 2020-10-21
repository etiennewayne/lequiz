<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class AndroidAcademicYear extends Controller
{
    //

    public function getAY(){
    	return \DB::table('academicyears')
    	->orderBy('ay_code', 'desc')
    	->get();


    }
}
