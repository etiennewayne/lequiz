<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Category;

class CategoryController extends Controller
{
    //

    public function index(){
        return view('category.category');
    }

    public function categories(){
        $user_id = \Auth::user()->user_id;
        $data = \DB::table('categories')->where('user_id', $user_id)->get();
        return $data;
    }


    public function create(){
        return view('category.categorycreate');
    }

    public function store(Request $req){
        $user_id = \Auth::user()->user_id;


        $validate = $req->validate([
            'category' => ['required', 'unique:categories,category,null,category_id,user_id,'.$user_id]
        ]);


        $data = Category::create([
            'user_id' => $user_id,
            'category' => strtoupper($req->category),
            'category_desc' => strtoupper($req->category_desc)
        ]);

        return redirect('/category')->with('success', 'Category successfully added.');
    }

    public function edit($id){
        $category = Category::find($id);

        return view('category.categoryedit', ['category' => $category]);
    }


    public function update(Request $req, $id){


        $user_id = \Auth::user()->user_id;
        $category = Category::find($id);

        if($req->category != $category->category){
            $validate = $req->validate([
                'category' => ['required', 'unique:categories']
            ]);
        }

        $category->user_id = $user_id;
        $category->category = strtoupper($req->category);
        $category->category_desc = strtoupper($req->category_desc);
        $category->save();
        return redirect('/category')->with('success', 'Category successfully Updated.');
    }


    public function destroy($id)
    {
        //
        Category::destroy($id);

    }
}
