document.addEventListener('DOMContentLoaded', function () {
	var tableBody = document.getElementById('my-table-data');
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.onreadystatechange = function () {
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			var data = JSON.parse(xmlHttpRequest.responseText);
			var newContent = ''; 
			for (let i = 0; i < data.length; i++) {
				newContent += `
                <tr>
                    <td>${data[i].id}</td>
                    <td>${data[i].name}</td>
                    <td>${data[i].salary}</td>
                    <td>
                        <a href="/form.html?id=${data[i].id}" class="btn-edit">Edit</a> 
                    </td>
                </tr>`;
			}
			tableBody.innerHTML = newContent;
		}
	};
	xmlHttpRequest.open('get', 'http://localhost:8080/api/employees', false);
	xmlHttpRequest.send();
});
