<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Category;
use App\Academicyear;


class CategoryController extends Controller
{
    //

    public function __construct()
    {
        $this->middleware('auth');
        $this->middleware('faculty');
    }

    public function index(){


        return view('category.category');
    }

    public function categories(){
        $user_id = \Auth::user()->user_id;

        $data = \DB::table('categories as a')
        ->join('academicyears as b', 'a.academic_year_id', 'b.academic_year_id')->where('a.user_id', $user_id)->get();
        return $data;
    }


    public function create(){
        $ay = Academicyear::orderBy('ay_code', 'desc')
        ->get();


        return view('category.categorycreate')
        ->with('academicyear', $ay);
    }

    public function store(Request $req){
        $user_id = \Auth::user()->user_id;

        $ay = Academicyear::where('active', 1)->first();

        $validate = $req->validate([
            'category' => ['required', 'unique:categories,category,null,category_id,user_id,'.$user_id],
             'unit' => ['required', 'integer']
        ]);


        $data = Category::create([
            'user_id' => $user_id,
            'academic_year_id' => $ay->academic_year_id,
            'category' => strtoupper($req->category),
            'category_desc' => strtoupper($req->category_desc),
            'unit' => $req->unit
        ]);

        return redirect('/category')->with('success', 'Category successfully added.');
    }

    public function edit($id){
        $category = Category::find($id);
         $ay = Academicyear::orderBy('ay_code', 'desc')
        ->get();


        return view('category.categoryedit', ['category' => $category, 'academicyear' => $ay]);
    }


    public function update(Request $req, $id){


        $user_id = \Auth::user()->user_id;
        $category = Category::find($id);


        $ay = Academicyear::where('active', 1)->first();

        if($req->category != $category->category){
            $validate = $req->validate([
                'category' => ['required', 'unique:categories'],
                'unit' => ['required', 'number']
            ]);
        }

        $category->user_id = $user_id;
        $category->academic_year_id = $ay->academic_year_id;
        $category->category = strtoupper($req->category);
        $category->category_desc = strtoupper($req->category_desc);
        $category->unit = $req->unit;
        $category->save();
        return redirect('/category')->with('success', 'Category successfully Updated.');
    }


    public function destroy($id)
    {
        //
        Category::destroy($id);

    }
}
