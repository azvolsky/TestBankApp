var BootstrapTable = ReactBootstrapTable.BootstrapTable;
var TableHeaderColumn = ReactBootstrapTable.TableHeaderColumn;

var Table = React.createClass({

    getInitialState: function() {
        return {data: []};
    },

    componentDidMount: function() {
           console.log(" componentDidMount ");
           $.ajax({
             type: 'GET',
             url: 'http://localhost:8080/rest/tx',
             dataType: 'json',
             cache: false,
             success: function(data) {
               this.setState({data: data});
             }.bind(this),
             error: function(xhr, status, err) {
               console.error(this.props.url, status, err.toString());
             }.bind(this)
           });
    },

    onAfterSaveCell: function(row, value, name) {
            console.log(" onAfterSaveCell ");
            console.log("row ID: " + row.id);
            console.log("row name: " + row.name);
            console.log("row topics: " + row.topics);
            console.log("row rp: " + row.rp);
            console.log("value: " + value);
            console.log("name: " + name);
       $.ajax({
           method:'GET',
           url:'http://localhost:8080/critters/sections/update',
           dataType: 'json',
           headers: {
               'Accept': 'application/json',
               'Content-Type': 'application/json'
           },
           data:{
                'id' : row.id,
                'name' : row.name,
                'topics' : row.topics,
                'rp' : row.rp
           }
       })
       .then((response)=>{
           this.getCustomerData();
       })
       .catch((error)=>{
           throw('error',error);
       });
   },

    handleInsertedRow: function(row){
        console.log(" handleInsertedRow ");
        console.log("row ID" + row.id);
        console.log("row name" + row.name);
        console.log("row topics" + row.topics);
        console.log("row rp" + row.rp);
          $.ajax({
              method:'GET',
              url:'http://localhost:8080/critters/sections/create',
              dataType: 'json',
              headers: {
                  'Accept': 'application/json',
                  'Content-Type': 'application/json'
              },
              data:{
                'name': row.name,
                'topics': row.topics,
                'rp' : row.rp
              }
          })
          .then((response)=>{
//              this.getCustomerData();
          })
          .catch((error)=>{
              throw('error',error);
          });
    },

    handleDeletedRow: function(rowKeys){
              console.log("rowKeys " + rowKeys);
              $.ajax({
                  method:'GET',
                  url:'http://localhost:8080/critters/sections/delete/' + rowKeys,
              })
              .then((response)=>{
              })
              .catch((error)=>{
                  throw('error',error);
              });
    },

   render() {

        const cellEditProp = {
            mode: "click",
            blurToSave: true,
            afterSaveCell: this.onAfterSaveCell.bind(this)
        }

        const selectRowProp = {
            mode: "checkbox",
            clickToSelect: true,
            bgColor: "rgb(238, 193, 213)"
        };

        const options = {
            afterDeleteRow: this.handleDeletedRow.bind(this),
            afterInsertRow: this.handleInsertedRow.bind(this)
        };

      return (
             <BootstrapTable
               data={this.state.data}
               selectRow={selectRowProp}
               cellEdit={cellEditProp}
               striped
               hover
               condensed
               pagination
               insertRow
               deleteRow
               options={options}
               search>
                 <TableHeaderColumn dataField="id" isKey={true} dataAlign="right" dataSort >id</TableHeaderColumn>
                 <TableHeaderColumn dataField="accountDst" dataSort editable={{type: 'textarea', validator: fieldValidator}}>name</TableHeaderColumn>
                 <TableHeaderColumn dataField="accountSrc" dataAlign="center" editable={ { type: 'textarea', validator: fieldValidator } } >address</TableHeaderColumn>
                 <TableHeaderColumn dataField="transfer" dataAlign="center" editable={ { type: 'textarea' } }>age</TableHeaderColumn>
                 <TableHeaderColumn dataField="start" dataAlign="center" editable={ { type: 'textarea' } }>age</TableHeaderColumn>
                 <TableHeaderColumn dataField="end" dataAlign="center" editable={ { type: 'textarea' } }>age</TableHeaderColumn>
             </BootstrapTable>
      )
   }
});

//dataFormat={showTopics}

function showTopics(cell, row) {
  return cell.topics;
}

function fieldValidator(value, row) {
  const response = { isValid: true, notification: { type: 'success', msg: '', title: '' } };
  if (!value) {
    response.isValid = false;
    response.notification.type = 'error';
    response.notification.msg = 'Value must be inserted';
    response.notification.title = 'Requested Value';
  } else if (value.length < 3) {
    response.isValid = false;
    response.notification.type = 'error';
    response.notification.msg = 'Value must have 3+ characters';
    response.notification.title = 'Invalid Value';
  }
  return response;
}

 ReactDOM.render(

     <Table />,
     document.getElementById('Sections')

 );