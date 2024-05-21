$(document).ready(() => {
  $("#table-region").DataTable({
    ajax: {
      url: "/api/region",
      dataSrc: "",
    },
    columnDefs: [{ className: "text-center", targets: "_all" }],
    columns: [
      { data: "id" },
      { data: "name" },
      {
        data: null,
        render: (data) => {
          return /*html*/ `
            <div class="d-flex gap-4 justify-content-center align-items-center">
              <button
                type="button"
                class="btn btn-primary btn-sm d-flex align-items-center"
                data-bs-toggle="modal"
                data-bs-target="#detail"
                title="Detail ${data.name}"
                onclick="findById(${data.id})"
              >
                <span class="material-symbols-rounded"> info </span>
              </button>
              <button
                type="button"
                class="btn btn-warning btn-sm d-flex align-items-center"
                data-bs-toggle="modal"
                data-bs-target="#update"
                title="Update ${data.name}"
                onclick="beforeUpdate(${data.id})"
              >
                <span class="material-symbols-rounded"> update </span>
              </button>
              <button
                type="button"
                class="btn btn-danger btn-sm d-flex align-items-center"
                title="Delete ${data.name}"
                onclick="deleteRegion(${data.id})"
              >
                <span class="material-symbols-rounded"> delete </span>
              </button>
            </div>
          `;
        },
      },
    ],
  });
});

let findById = (id) => {
  // console.log(id);
  $.ajax({
    method: "GET",
    url: "/api/region/" + id,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      // console.log(res);
      $("#detail-id").val(res.id);
      $("#detail-name").val(res.name);
    },
    error: (err) => {
      console.log(err);
    },
  });
};

$("#create-region").click((event) => {
  event.preventDefault();

  let valueName = $("#create-name").val();
  // console.log(valueName);

  if (valueName === "" || valueName === null) {
    Swal.fire({
      position: "center",
      icon: "error",
      title: "Please fill all field!!!",
      showConfirmButton: false,
      timer: 1500,
    });
  } else {
    $.ajax({
      method: "POST",
      url: "/api/region",
      dataType: "JSON",
      contentType: "application/json",
      data: JSON.stringify({
        name: valueName,
      }),
      success: (res) => {
        // console.log(res);
        $("#create").modal("hide");
        $("#table-region").DataTable().ajax.reload();
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Region has been created...",
          showConfirmButton: false,
          timer: 1500,
        });
        $("#create-name").val("");
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
});

function beforeUpdate(id) {
  // console.log(id);
  $.ajax({
    method: "GET",
    url: "/api/region/" + id,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      // console.log(res);
      $("#update-id").val(res.id);
      $("#update-name").val(res.name);
    },
    error: (err) => {
      console.log(err);
    },
  });
}

$("#update-region").click((event) => {
  event.preventDefault();

  let valueId = $("#update-id").val();
  let valueName = $("#update-name").val();
  // console.log(valueName);

  if (valueName === "" || valueName === null) {
    Swal.fire({
      position: "center",
      icon: "error",
      title: "Please fill all field!!!",
      showConfirmButton: false,
      timer: 1500,
    });
  } else {
    $.ajax({
      method: "PUT",
      url: "/api/region/" + valueId,
      dataType: "JSON",
      contentType: "application/json",
      data: JSON.stringify({
        name: valueName,
      }),
      success: (res) => {
        console.log(res);
        $("#update").modal("hide");
        $("#table-region").DataTable().ajax.reload();
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Region has been updated...",
          showConfirmButton: false,
          timer: 1500,
        });
        $("#update-name").val("");
        $("#update-id").val("");
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
});

function deleteRegion(id) {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger",
    },
    buttonsStyling: false,
  });
  swalWithBootstrapButtons
    .fire({
      title: "Are you sure?",
      text: "You won't be able to delete this!",
      icon: "warning",
      showCancelButton: true,
      confirmButtonText: "Yes, delete it!",
      cancelButtonText: "No, cancel!",
      reverseButtons: true,
      padding: "20px",
    })
    .then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          method: "DELETE",
          url: "/api/region/" + id,
          dataType: "JSON",
          contentType: "application/json",
          success: (res) => {
            // console.log(res);
            $("#table-region").DataTable().ajax.reload();
          },
          error: (err) => {
            console.log(err);
          },
        });
        swalWithBootstrapButtons.fire({
          title: "Deleted!",
          text: "Your Region has been deleted.",
          icon: "success",
        });
      } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
      ) {
        swalWithBootstrapButtons.fire({
          title: "Cancelled",
          text: "Your imaginary Region is safe :)",
          icon: "error",
        });
      }
    });
  document.querySelector(".swal2-actions").style.marginCenter = "25px";
}
