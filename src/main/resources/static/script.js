const API_URL = "http://localhost:8080/api/expenses";

document.getElementById("expenseForm").addEventListener("submit", saveExpense);

function saveExpense(e) {
    e.preventDefault();

    const id = document.getElementById("expenseId").value;

    const expense = {
        title: title.value,
        amount: amount.value,
        category: category.value,
        expenseDate: expenseDate.value
    };

    const method = id ? "PUT" : "POST";
    const url = id ? `${API_URL}/${id}` : API_URL;

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(expense)
    }).then(() => {
        resetForm();
        loadExpenses();
    });
}

function loadExpenses() {
    fetch(API_URL)
        .then(res => res.json())
        .then(data => {
            const table = document.getElementById("expenseTableBody");
            table.innerHTML = "";

            data.forEach(e => {
                table.innerHTML += `
                    <tr>
                        <td>${e.title}</td>
                        <td>₹${e.amount}</td>
                        <td>${e.category}</td>
                        <td>${e.expenseDate}</td>
                        <td>
                            <button class="btn btn-sm btn-warning" onclick="editExpense(${e.id})">Edit</button>
                            <button class="btn btn-sm btn-danger" onclick="deleteExpense(${e.id})">Delete</button>
                        </td>
                    </tr>
                `;
            });
        });
}

function editExpense(id) {
    fetch(`${API_URL}/${id}`)
        .then(res => res.json())
        .then(e => {
            expenseId.value = e.id;
            title.value = e.title;
            amount.value = e.amount;
            category.value = e.category;
            expenseDate.value = e.expenseDate;
        });
}

function deleteExpense(id) {
    if (!confirm("Delete this expense?")) return;

    fetch(`${API_URL}/${id}`, { method: "DELETE" })
        .then(() => loadExpenses());
}

function resetForm() {
    expenseId.value = "";
    expenseForm.reset();
}

loadExpenses();
