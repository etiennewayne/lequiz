<?php

namespace App\Http\Controllers;
use App\Academicyear;
use Validator;
use App\Semester;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;


class AcademicYearController extends Controller
{
    //

    public function __construct()
    {
        $this->middleware('auth');
    }


    public function index(){

        // $academicyears = DB::table('vw_academicyears')->get();

    	return view('academicyear/academicyear');
    }


    public function store(Request $request){

       $validator = $request->validate([
            'ay_code' => ['string','max:10', 'required', 'unique:academicyears'],
            'ay' => ['required', 'max:10','string'],
            'semester_id' => ['required']
       ]);

       Academicyear::create([
            'ay_code' => $request->ay_code,
            'ay' => $request->ay,
            'semester_id' => $request->semester_id
       ]);


        return redirect('/academicyear')->with('success','Academic year successfully created.');

    }

    public function create(){
        $semesters = Semester::all();

		return view('academicyear/academicyearcreate')->with('semesters', $semesters);
    }


    public function edit($id){

        $semesters = Semester::orderBy('semester', 'asc')
        ->get();


        $academicyear = Academicyear::find($id);

       
        return view('academicyear/academicyearedit')
        ->with('academicyear', $academicyear)
        ->with('semesters',$semesters);


    }


    public function update(Request $request, $id){

         $academicyear = Academicyear::find($id);

         $academicyear->ay_code = $request->ay_code;
         $academicyear->ay = $request->ay;
         $academicyear->semester_id = $request->semester_id;
         $academicyear->save();

         return redirect('/academicyear')
         ->with('updated', 'Academic setup successfully updated.');
    }

    public function destroy($id){
        Academicyear::destroy($id);
        return redirect('/academicyear')
        ->with('deleted','Successfully deleted.');
    }



    public function markactive($id){
        DB::update('update academicyears set active = 0');
        DB::update('update academicyears set active = 1 where academicyearID=?', [$id]);

        return redirect('/academicyear')
         ->with('active', 'Set successfully.');
     // return $id;
    }

    public function data_ay(){

        $data = \DB::table('academicyears as a')
        ->join('semesters as b','a.semester_id', 'b.semester_id')
        ->select('a.academic_year_id', 'ay_code', 'a.ay', 'a.semester_id', 'b.semester', 'a.active')
        ->get();
        return $data;
    }

}
