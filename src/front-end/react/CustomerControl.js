import React from 'react';
import { Component } from 'react';



export default class CustomerControl extends Component {
  constructor(props) {
    super(props);

    this.state = {customers: []};
  }
  componentDidMount() {

    // console.log(this.props.)


  }
  
  
   formatMoney(amount, decimalCount = 2, decimal = ".", thousands = ",") {
    try {
      decimalCount = Math.abs(decimalCount);
      decimalCount = isNaN(decimalCount) ? 2 : decimalCount;

      const negativeSign = amount < 0 ? "-" : "";

      let i = parseInt(amount = Math.abs(Number(amount) || 0).toFixed(decimalCount)).toString();
      let j = (i.length > 3) ? i.length % 3 : 0;

      return negativeSign + (j ? i.substr(0, j) + thousands : '') + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousands) + (decimalCount ? decimal + Math.abs(amount - i).toFixed(decimalCount).slice(2) : "");
    } catch (e) {
      console.log(e)
  }
  }
  
  
  
  
  
  static getDerivedStateFromProps(nextProps, prevState) {
  //  console.log(nextProps.customers);
    return {customers: nextProps.customers};

  }
  composeCustomers() {
    let customers = [];
   
    this.state.customers.forEach(c => {
       let v = this.formatMoney(c.salesTotal.toFixed(2), 0, ".", ",");
      customers.push(<tr key={c.customerNumber}><td>{c.customerName}</td><td>{c.customerNumber}</td><td>$ {v}</td></tr>);

    })
    return customers;
  }
  render() {

    if (this.state.customers.length > 0) {

      return (
            <div>
                <div className="row"><h3>Customers</h3></div>
                <table className="table table-striped">
                    <thead>
                        <tr><th>Name</th><th>ID</th><th>Sales</th></tr>
                    </thead>
                    <tbody>
                        {this.composeCustomers()} 
                    </tbody>
            
                </table>
            </div>
            )

    }

    return <div></div>



  }
}