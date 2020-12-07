@extends('layouts.app')

@section('content')


<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/jszip.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/xlsx/0.8.0/xlsx.js"></script>
<script>
    var ExcelToJSON = function() {

      this.parseExcel = function(file) {
        var reader = new FileReader();

        reader.onload = function(e) {
          var data = e.target.result;
          var workbook = XLSX.read(data, {
            type: 'binary'
          });
          workbook.SheetNames.forEach(function(sheetName) {
            // Here is your object
            var XL_row_object = XLSX.utils.sheet_to_row_object_array(workbook.Sheets[sheetName]);
            var json_object = JSON.stringify(XL_row_object);
            console.log(JSON.parse(json_object));
            jQuery( '#xlx_json' ).val( json_object );
          })
        };

        reader.onerror = function(ex) {
          console.log(ex);
        };

        reader.readAsBinaryString(file);
      };
  };

  function handleFileSelect(evt) {
    
    var files = evt.target.files; // FileList object
    var xl2json = new ExcelToJSON();
    xl2json.parseExcel(files[0]);
  }


 
</script>


<div class="container mt-5">
    <div class="row mb-2">

        <form enctype="multipart/form-data" method="post" action="/quiz/question/uploader/39/store">

            <div class="input-group mb-3">
              <div class="input-group-prepend">
                <span class="input-group-text">Upload</span>
              </div>
              <div class="custom-file">
                 {{-- <input id="upload" type="file" class="form-control"  name="files[]"> --}}
                <input type="file" class="custom-file-input" id="upload" name="files[]">
                <label class="custom-file-label" for="inputGroupFile01">Choose file</label>
              </div>
            </div>


            @csrf

            <input type="hidden" name="_quiz_id" value="{{ $quizid }}">
            <div class="form-group">
                
            </div>

            <textarea readonly class="form-control" name="question_json" rows=20 cols=120 id="xlx_json"></textarea>

           
           <div class="row mt-2" style="float: right;">
              <button class="btn btn-primary">Upload</button>
          </div>


        </form>

        

    </div>


    

    <div class="clearfix"></div>

    

    <script>
        document.getElementById('upload').addEventListener('change', handleFileSelect, false);
    </script>




</div>



@endsection