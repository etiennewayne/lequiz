<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Category extends Model
{
    //
    protected $table = 'categories';
    protected $fillable = ['user_id','category', 'category_desc'];
    protected $primaryKey = 'category_id';

}
