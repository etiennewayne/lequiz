<?php

namespace App\Http\Controllers;
use App\Quiz;

use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;


class AndroidQuizController extends Controller
{
    //

    public function index($username){
        $quizes = DB::table('quizes')
        ->where('username', $username)->get();
        return $quizes;
    }



    public function quizzes($user_id){
    	return \DB::table('quizzes as a')
    	->join('categories as b', 'a.category_id', 'b.category_id')
    	->where('a.user_id', $user_id)
    	->get();
    }

    public function store(Request $req){
       // return $req;

        $data = \DB::table('categories')
        ->where('category', $req->category)
        ->first();

        $cat_id = $data->category_id;


        \DB::table('quizzes')->insert([
            'user_id' => $req->user_id,
            'category_id' => $cat_id,
            'quiz_title' => strtoupper($req->quiz_title),
            'quiz_desc' => strtoupper($req->quiz_desc)
        ]);

        return ['status' => 'saved'];

    }


    public function update(Request $req){

        $data = \DB::table('categories')
        ->where('category', $req->category)
        ->first();
        $cat_id = $data->category_id;


        $quiz = Quiz::find($req->quiz_id);
        $quiz->category_id = $cat_id;
        $quiz->quiz_title = $req->quiz_title;
        $quiz->quiz_desc = $req->quiz_desc;
        $quiz->save();

        return ['status' => 'updated'];

    }

    public function edit($quiz_id){
        return \DB::table('quizzes as a')
        ->join('categories as b', 'a.category_id', 'b.category_id')
        ->where('quiz_id', $quiz_id)
        ->get();
    }

    public function delete(Request $req){
    	Quiz::destroy($req->quiz_id);
    	return ['status' => 'deleted'];
    }

}



