$(document).ready(() => {
  $("#table-country").DataTable({
    ajax: {
      url: "/api/country",
      dataSrc: "",
    },
    columnDefs: [{ className: "text-center", targets: "_all" }],
    columns: [
      { data: "id" },
      { data: "code" },
      { data: "name" },
      { data: "region.name" },
      {
        data: null,
        render: (data) => {
          return /*html*/ `
                    <div class="d-flex gap-4 justify-content-center align-items-center">
                        <button type="button"
                        class="btn btn-primary btn-sm d-flex align-items-center"
                                data-bs-toggle="modal"
                                data-bs-target="#detail-modal"
                                title="Detail ${data.name}"
                                onclick="findById(${data.id})">
                                <span class="material-symbols-rounded"> info </span>
                        </button>
                        <button type="button"
                        class="btn btn-warning btn-sm d-flex align-items-center"
                                data-bs-toggle="modal"
                                data-bs-target="#update-modal"
                                title="Update ${data.name}"
                                onclick="beforeUpdate(${data.id})">
                                <span class="material-symbols-rounded"> update </span>
                        </button>
                        <button type="button"
                        class="btn btn-danger btn-sm d-flex align-items-center"
                                title="Delete ${data.name}"
                                onclick="deleteCountry(${data.id})">
                                 <span class="material-symbols-rounded"> delete </span>
                        </button>
                    </div>
                    `;
        },
      },
    ],
  });

  $.ajax({
    method: "GET",
    url: "/api/region",
    success: function (data) {
      let createSelectRegion = $("#create-country-region");
      let updateSelectRegion = $("#update-country-region");

      createSelectRegion.empty();
      updateSelectRegion.empty();

      createSelectRegion.append(
        '<option value="" selected>Select Region</option>',
      );
      updateSelectRegion.append(
        '<option value="" selected>Select Region</option>',
      );

      $.each(data, function (key, region) {
        createSelectRegion.append(
          '<option value="' + region.id + '">' + region.name + "</option>",
        );
        updateSelectRegion.append(
          '<option value="' + region.id + '">' + region.name + "</option>",
        );
      });
    },
    error: function (err) {
      console.log(err);
    },
  });
});

$("#create-new-country").click((event) => {
  event.preventDefault();
  let valueCode = $("#create-country-code").val();
  let valueName = $("#create-country-name").val();
  let valueRegion = $("#create-country-region").val();

  if (
    valueName === "" ||
    (valueName === null && valueCode === "") ||
    (valueCode === null && valueRegion === "")
  ) {
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
      url: "/api/country",
      dataType: "JSON",
      contentType: "application/json",
      data: JSON.stringify({
        code: valueCode,
        name: valueName,
        regionId: valueRegion,
      }),
      success: (res) => {
        console.log(res);
        $("#create-modal").modal("hide");
        $("#table-country").DataTable().ajax.reload();
        $("#create-country-code").val("");
        $("#create-country-name").val("");
        Swal.fire({
          position: "center",
          icon: "success",
          title: "New Country has been created!",
          showConfirmButton: false,
          timer: 1500,
        });
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
});

let findById = (id) => {
  $.ajax({
    method: "GET",
    url: "/api/country/" + id,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $("#detail-country-id").val(res.id);
      $("#detail-country-code").val(res.code);
      $("#detail-country-name").val(res.name);
      $("#detail-country-region").val(res.region.name);
    },
    error: (err) => {
      console.log(err);
    },
  });
};

function beforeUpdate(id) {
  $.ajax({
    method: "GET",
    url: "/api/country/" + id,
    dataType: "JSON",
    contentType: "application/json",
    success: (res) => {
      $("#update-country-id").val(res.id);
      $("#update-country-code").val(res.code);
      $("#update-country-name").val(res.name);
    },
    error: (err) => {
      console.log(err);
    },
  });
}

$("#btn-update-country").click((event) => {
  event.preventDefault();
  let valueId = $("#update-country-id").val();
  let valueCode = $("#update-country-code").val();
  let valueName = $("#update-country-name").val();
  let valueRegion = $("#update-country-region").val();

  if (valueName === "" || valueName === null) {
    Swal.fire({
      position: "center",
      icon: "error",
      title: "Please fill all field!!!",
      showConfirmButton: false,
      timer: 1500,
    });
  } else {
    console.log(valueId);
    console.log(valueName);
    $.ajax({
      method: "PUT",
      url: "/api/country/" + valueId,
      dataType: "JSON",
      contentType: "application/json",
      data: JSON.stringify({
        code: valueCode,
        name: valueName,
        regionId: valueRegion,
      }),
      success: (res) => {
        $("#update-modal").modal("hide");
        $("#table-country").DataTable().ajax.reload();
        Swal.fire({
          position: "center",
          icon: "success",
          title: "Country has been updated!",
          showConfirmButton: false,
          timer: 1500,
        });
        $("#update-country-id").val("");
        $("#update-country-code").val("");
        $("#update-country-name").val("");
      },
      error: (err) => {
        console.log(err);
      },
    });
  }
});

function deleteCountry(id) {
  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success swal-button",
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
    })
    .then((result) => {
      if (result.isConfirmed) {
        $.ajax({
          method: "DELETE",
          url: "/api/country/" + id,
          dataType: "JSON",
          contentType: "application/json",
          success: (res) => {
            $("#table-country").DataTable().ajax.reload();
          },
          error: (err) => {
            console.log(err);
          },
        });
        swalWithBootstrapButtons.fire({
          title: "Deleted!",
          text: "Your Country has been deleted.",
          icon: "success",
        });
      } else if (result.dismiss === Swal.DismissReason.cancel) {
        swalWithBootstrapButtons.fire({
          title: "Cancelled",
          text: "Your imaginary Country is safe :)",
          icon: "error",
        });
      }
    });
}
