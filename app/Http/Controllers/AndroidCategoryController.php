<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Category;

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
    		'category' => strtoupper($req->category),
    		'category_desc' => strtoupper($req->category_desc)
    	]);

    	return ['status' => 'saved'];
    }

    public function update(Request $req){

    	//return  $req;

    	$data = \DB::table('academicyears')
    	->where('ay_code', $req->aycode)
    	->first();

    	$ayid = $data->academic_year_id;
    	//return $ayid;

    	$cat = Category::find($req->category_id);
    	$cat->academic_year_id = $ayid;
    	$cat->category = $req->category;
    	$cat->category_desc = $req->category_desc;
    	$cat->save();
    	

    	 return ['status' => 'updated'];
    }



    public function getCategories($userid){
    	return \DB::table('categories as a')
    	->join('academicyears as b', 'a.academic_year_id', 'b.academic_year_id')
    	->where('user_id', $userid)
    	->orderBy('category', 'asc')
    	->get();
    }


    public function delete(Request $req){
    	//return $req;
    	Category::destroy($req->category_id);
    	return ['status' => 'deleted'];
    }

    public function edit($cat_id){
    	return \DB::table('categories as a')
    	->join('academicyears as b', 'a.academic_year_id', 'b.academic_year_id')
    	->where('category_id', $cat_id)
    	->get();
    }


}
