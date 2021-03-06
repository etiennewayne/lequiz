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
            'question' => ['string', 'required'],
            'opt_a' => ['string', 'required'],
            'opt_b' => ['string', 'required'],
            'opt_c' => ['string', 'required'],
            'opt_d' => ['string', 'required'],
            'ans' => ['string', 'required'],
            'time_limit' => ['int', 'required', 'numeric', 'gt:0'],
            'points' => ['int', 'required', 'numeric', 'gt:0'],
        ]);

    

        $data = Question::create([
            'question' => $request->question,
            'opt_a' => $request->opt_a,
            'opt_b' => $request->opt_b,
            'opt_c' => $request->opt_c,
            'opt_d' => $request->opt_d,
            'ans' => $request->ans,
            'quiz_id' => $request->quiz_id,
            'set_time' => $request->time_limit,
            'equiv_score' => $request->points,
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
        $question->set_time = $request->time_limit;
        $question->equiv_score = $request->points;
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
         $data = \DB::table('quizzes as a')
        ->join('questions as b', 'a.quiz_id','b.quiz_id')
        ->where('a.access_code', $acode)
        ->select('a.quiz_id', 'a.quiz_title', 'a.quiz_desc',
            'b.question_id', 'b.question', 'b.opt_a', 'b.opt_b', 'b.opt_c', 'b.opt_d', 'b.ans', 'b.set_time', 'b.equiv_score')
            ->get();
        //return Quiz::with(['user', 'category'])->get();
        return $data;
    }



  

   public function uploader($quizid){
       
        return view('questions.question-uploader')
        ->with('quizid', $quizid);
    }

    public function storeUpload(Request $req){
        //return $req;
       
        $arr = json_decode($req->question_json);
        //return $arr;
        //echo json_decode($req->question_json);

        foreach($arr as $item) { //foreach element in $arr
            //echo $item->question; //etc
            Question::create([
                'question' => $item->question,
                'opt_a' => $item->option_a,
                'opt_b' => $item->option_b,
                'opt_c' => $item->option_c,
                'opt_d' => $item->option_d,
                'ans' => $item->answer,
                'quiz_id' => $req->_quiz_id,
                'set_time' => $item->set_time,
                'equiv_score' => $item->equivalent_score

            ]);          
        }
        
        return redirect('/quiz/'. $req->_quiz_id .'/question');

    }
 




}
