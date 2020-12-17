<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class AndroidQuizGameController extends Controller
{
    //

    public function store(Request $req){
    	\DB::table('student_quizzes')
    	->insert([
    		'user_id' => $req->user_id,
    		'quiz_id' => $req->quiz_id,
    		'total_score' => $req->total_score
    	]);
        return ['status' => 'saved'];
    }
    
}
