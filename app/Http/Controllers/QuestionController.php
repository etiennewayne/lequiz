<?php

namespace App\Http\Controllers;
use App\Question;

//use App\Quiz;
//use Validator;
use Illuminate\Support\Facades\DB;
use Illuminate\Http\Request;

class QuestionController extends Controller
{
    //

    public function __construct()
    {
        $this->middleware('auth');
    }

    public function index($quizid){

        $questions = DB::table('questions')
        ->where('quiz_id', $quizid)
        ->get();

        // $questions = DB::table('questions')
        // ->get();

        //$questions = Question::all();

       return view('questions/questions')->with('questions', $questions)
       ->with('quizid', $quizid);
    }


    public function create($quizID){
        return view('questions/questioncreate')->with('quizid', $quizID);
    }

    public function store(Request $request){

        $validator = $request->validate([
            'question' => ['string', 'required', 'unique:questions'],
            'opt_a' => ['string', 'required'],
            'opt_b' => ['string', 'required'],
            'opt_c' => ['string', 'required'],
            'opt_d' => ['string', 'required'],
            'ans' => ['string', 'required'],
            'set_time' => ['int', 'required'],
            'equiv_score' => ['int', 'required'],
        ]);

        $data = Question::create([
            'question' => $request->question,
            'opt_a' => $request->opt_a,
            'opt_b' => $request->opt_b,
            'opt_c' => $request->opt_c,
            'opt_d' => $request->opt_d,
            'ans' => $request->ans,
            'quiz_id' => $request->quiz_id,
            'set_time' => $request->set_time,
            'equiv_score' => $request->equiv_score,
        ]);

        $qid = $request->quizid;
        return redirect('/quiz/'.$qid.'/question')->with('success','Question successfully created.');
    }


    public function edit($quizid, $questionid){


        $question = Question::find($questionid);
        return view('questions/questionedit')
        ->with('quizid', $quizid)
        ->with('question', $question);
    }


    public function update(Request $request, $quizid, $questionid){
        //$question = Question::find($)

        // $validator = $request->validate([
        //     'question' => ['string', 'required', 'unique:questions'],
        //     'optA' => ['string', 'required'],
        //     'optB' => ['string', 'required'],
        //     'optC' => ['string', 'required'],
        //     'optD' => ['string', 'required'],
        //     'ans' => ['string', 'required'],
        // ]);


        $question = Question::find($questionid);
        $question->question = $request->question;
        $question->opt_a = $request->opt_a;
        $question->opt_b = $request->opt_b;
        $question->opt_c = $request->opt_c;
        $question->opt_d = $request->opt_d;
        $question->ans = $request->ans;
        $question->set_time = $request->set_time;
        $question->equiv_score = $request->equiv_score;
        $question->save();


        return redirect('/quiz/'.$quizid.'/question')->with('updated','Question successfully updated.');
    }

    public function destroy($quizid, $questionid){
        Question::destroy($questionid);
        return redirect('/quiz/'. $quizid. '/question')
        ->with('deleted', 'Successfully deleted.');
    }


    public function questions($quizid){

        $data = \DB::table('questions as a')
        ->join('quizzes as b', 'a.quiz_id','b.quiz_id')
        ->where('a.quiz_id', $quizid)
        ->select('a.question_id', 'a.question', 'a.opt_a', 'a.opt_b', 'a.opt_c', 'a.opt_d', 'a.ans', 'a.quiz_id', 'b.quiz_title', 'a.set_time', 'a.equiv_score')
        ->get();
        //return Quiz::with(['user', 'category'])->get();
        return $data;
    }


    public function questionsByAccessCode($acode){
         $data = \DB::table('rooms as a')
        ->join('quizzes as b', 'a.quiz_id','b.quiz_id')
        ->join('questions as c', 'c.quiz_id','b.quiz_id')
        ->where('a.access_code', $acode)
        ->select('a.room_id', 'a.room', 'a.room_desc', 'a.access_code', 'a.quiz_id', 'b.quiz_title', 'b.quiz_desc', 'c.question', 'c.opt_a', 'c.opt_b', 'c.opt_c', 'c.opt_d', 'c.ans', 'c.set_time', 'c.equiv_score')
        ->get();
        //return Quiz::with(['user', 'category'])->get();
        return $data;
    }

}
