
  document.querySelectorAll('td').forEach(function(td) {
    var option = td.textContent.trim();
    if (option === '++') {
      td.style.backgroundColor = '#56b048';
    } else if (option === '--') {
      td.style.backgroundColor = '#fa5b37';
    } else if (option === '-') {
      td.style.backgroundColor = '#ffd3c9';
    } else if (option === '+') {
      td.style.backgroundColor = '#d1ffc9';
    } else if (option === '+-') {
      td.style.backgroundColor = '#dbdbdb';
    } else {
      td.style.backgroundColor = '';
    }
  });

