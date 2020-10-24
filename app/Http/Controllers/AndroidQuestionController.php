<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

use App\Question;

class AndroidQuestionController extends Controller
{
    //

    public function questions(Request $req){

    	return \DB::table('questions as a')
    	->join('quizzes as b', 'a.quiz_id', 'b.quiz_id')
    	->where('b.user_id', $req->user_id)
    	->where('a.quiz_id', $req->quiz_id)
    	->get();
    }

    public function store(Request $req){

        \DB::table('questions')->insert([
            'question' => $req->question,
            'opt_a' => $req->opt_a,
            'opt_b' => $req->opt_b,
            'opt_c' => $req->opt_c,
            'opt_d' => $req->opt_d,
            'ans' => $req->ans,
            'quiz_id' => $req->quiz_id,
            'set_time' => $req->set_time,
            'equiv_score' => $req->equiv_score
        ]);

        return ['status' => 'saved'];

    }


    public function update(Request $req){

    	//return $req;


        $data = Question::find($req->question_id);
      
        $data->question = $req->question;
        $data->opt_a = $req->opt_a;
        $data->opt_b = $req->opt_b;
        $data->opt_c = $req->opt_c;
        $data->opt_d = $req->opt_d;
        $data->ans = $req->ans;
        $data->quiz_id = $req->quiz_id;
        $data->set_time = $req->set_time;
        $data->equiv_score = $req->equiv_score;
        $data->save();

        return ['status' => 'updated'];

    }

    public function edit($question_id){
        return \DB::table('questions as a')
        ->join('quizzes as b', 'a.quiz_id', 'b.quiz_id')
        ->where('a.question_id', $question_id)
        ->get();
    }

    public function delete(Request $req){
    	//r//eturn $req;
    	Question::destroy($req->question_id);
    	return ['status' => 'deleted'];
    }



}
