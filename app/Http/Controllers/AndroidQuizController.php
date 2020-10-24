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

    public function delete(Request $req){
    	Quiz::destroy($req->quiz_id);
    	return ['status' => 'deleted'];
    }

}



