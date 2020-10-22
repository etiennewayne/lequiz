<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class AndroidCategoryController extends Controller
{
    //

    public function store(Request $req){
    	//return  $req;

    	$data = \DB::table('academicyears')
    	->where('ay_code', $req->aycode)
    	->first();

    	$ayid = $data->academic_year_id;
    	//return $ayid;
    	
    	\DB::table('categories')->insert([
    		'user_id' => $req->user_id,
    		'academic_year_id' => $ayid,
    		'category' => $req->category,
    		'category_desc' => $req->category_desc
    	]);

    	return ['status' => 'saved'];
    }

    public function getCategories(){
    	return \DB::table('categories')
    	->orderBy('category', 'asc')
    	->get();
    }


}
