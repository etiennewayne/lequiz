<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Category extends Model
{
    //
    protected $table = 'categories';
    protected $fillable = ['user_id', 'academic_year_id','category', 'category_desc', 'unit'];
    protected $primaryKey = 'category_id';

}
