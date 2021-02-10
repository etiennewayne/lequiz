<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Quiz;
use App\Category;
use Illuminate\Support\Facades\DB;

use Validator;

class QuizController extends Controller
{

    public function __construct()
    {
        $this->middleware('auth');
        $this->middleware('faculty');
    }


    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        //
        //$quizes = Quiz::all();
        $quizes = DB::table('quizzes')->get();
        return view('quizzes/quizzes');
        //return $quizes;
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
        $categories = \DB::table('categories')
            ->where('user_id', \Auth::user()->user_id)
            ->orderBy('category', 'asc')
            ->get();

        return view('quizzes/quizzescreate')
        ->with('categories', $categories);
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        //
        $validator = $request->validate([

            'access_code' => ['required', 'string', 'max:8', 'unique:quizzes'],
            'quiz_title' => ['required', 'string', 'max:100', 'unique:quizzes'],
            'quiz_desc' => ['required', 'string', 'max:255'],
        ]);


        $data = Quiz::create([
           // 'courseID' => $request->courseid,
            'user_id' => \Auth::user()->user_id,
            'schedule_code' => $request->schedule_code,
            'category_id' => $request->category_id,
            'access_code' => $request->access_code,
            'quiz_title' => strtoupper($request->quiz_title),
            'quiz_desc' => strtoupper($request->quiz_desc),

        ]);

        return redirect('/quiz')->with('success','Quizzes successfully created.');

    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {

        //
        $categories = \DB::table('categories')
            ->where('user_id', \Auth::user()->user_id)
            ->orderBy('category', 'asc')
            ->get();
       // // $quiz = \DB::select('select * from quizesviews where quizID = ?', [$id]);
       //  $quiz = Quiz::withg('quizesviews')
       //  ->where('quizID', $id)
       //  ->first();

        // $quiz = Quiz::with(['user', 'category'])
        // ->where('quiz_id', $id)->first();
    	  $quiz = Quiz::find($id);

          return view('quizzes/quizzesedit',
          ['quiz' => $quiz,
          'categories' => $categories]);
    //return $quiz->quizID;
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {

        $validator = $request->validate([
            'schedule_code' => ['required', 'string', 'max:10', 'unique:quizzes'],
            'access_code' => ['required', 'string', 'max:8'],
            'quiz_title' => ['required', 'string', 'max:100', 'unique:quizzes'],
            'quiz_desc' => ['required', 'string', 'max:255'],
        ]);



        $quiz = Quiz::find($id);

        $quiz->user_id = \Auth::user()->user_id;
        $quiz->schedule_code = $request->schedule_code;
        $quiz->category_id = $request->category_id;
    	$quiz->quiz_title = strtoupper($request->quiz_title);
        $quiz->quiz_desc = strtoupper($request->quiz_desc);
        $quiz->save();

        return redirect('/quiz')->with('updated','Quiz successfully updated.');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        //
        Quiz::destroy($id);
    	//return redirect('/quizes')->with('deleted','Successfully deleted.');
    }

    public function startQuiz($acode){
        return view('quizzes.quiz-start')
        ->with('accesscode', $acode);
    }

    public function quizzes(){

        $data = \DB::table('quizzes as a')
        ->join('users as b', 'a.user_id','b.user_id')
        ->join('categories as c', 'a.category_id', 'c.category_id')
        ->where('a.user_id', \Auth::user()->user_id)
        ->select('a.quiz_id', 'a.schedule_code', 'a.user_id', 'a.category_id', 'a.access_code', 'a.quiz_title', 'a.quiz_desc', 'c.category',
            'c.category_desc', 'b.username', 'b.lname', 'b.fname', 'b.mname',
            \DB::raw('(select sum(equiv_score) from questions where questions.quiz_id = a.quiz_id) as total_points')
            )
        ->get();
        //return Quiz::with(['user', 'category'])->get();
        return $data;
    }

    public function studentQuizzes($quizid){

        $quiz = DB::table('quizzes as a')
            ->join('student_quizzes as b', 'a.quiz_id', 'b.quiz_id')
            ->select('a.quiz_id', 'a.schedule_code', 'a.access_code', 'a.quiz_title', 'a.quiz_desc',
            'b.ay_code', 'b.total_score',
                \DB::raw('concat(date_format(b.created_at, "%b-%d-%Y"), " ", time_format(b.created_at, "%h:%i %p")) as created_at')
            )
            ->where('a.quiz_id', $quizid)
            ->get();

        $countquiz = DB::table('questions')
            ->where('quiz_id', $quizid)->count();

        return view('quizzes.student-quizzes')
            ->with('quiz', $quiz)
            ->with('countquiz', $countquiz)
            ->with('quizid', $quizid);
    }

    public function studentQuizzesAjax($quizid){
        $facultyid = \Auth::user()->user_id;

        $data = DB::table('student_quizzes as a')
        ->join('quizzes as b', 'a.quiz_id', 'b.quiz_id')
        ->join('users as c', 'a.user_id', 'c.user_id')
        ->join('categories as d', 'b.category_id', 'd.category_id')
        ->select(
            'a.quiz_id', 'c.idno', 'c.lname', 'c.fname', 'c.mname', 'a.total_score', 'd.category',
            'b.quiz_title', 'b.quiz_desc', 'b.access_code',
            \DB::raw('concat(date_format(a.created_at, "%b-%d-%Y"), " ", time_format(a.created_at, "%h:%i %p")) as created_at'),
            \DB::raw('(select sum(equiv_score) from questions where questions.quiz_id = a.quiz_id) as total_points')
        )
        ->where('a.quiz_id', $quizid)
        ->get();
        return $data;
    }
}
