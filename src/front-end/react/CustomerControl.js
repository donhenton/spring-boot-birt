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
  static getDerivedStateFromProps(nextProps, prevState) {
  //  console.log(nextProps.customers);
    return {customers: nextProps.customers};

  }
  composeCustomers() {
    let customers = [];

    this.state.customers.forEach(c => {
      customers.push(<tr key={c.customerNumber}><td>{c.customerName}</td><td>{c.customerNumber}</td><td>{c.salesTotal}</td></tr>);

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