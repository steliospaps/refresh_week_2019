import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import User from './User';
import UserCreator from './UserCreator.js';

export default class Users extends Component {
    render() {
	if(this.props.user == null) {
            return (
                <div>
		<Query query={gql`query {users:getAllUsers{id,name}}`} >
		  {({ loading, error, data }) => {
		      if (loading) return <div>Fetching</div>;
		      if (error) return <div>Error</div>;
		      
		      return (
			  <div>{data.users.map(user => <User key={user.id} user={user} onLogin={this.props.onLogin} />)}</div>
		      );
		  }}
		</Query>
                <UserCreator/>
                </div>
            );
	} else {
            return (
                <div>
                  <label>{this.props.user.name}</label> <button onClick={()=>this.props.onLogin(null)}>Logout</button>
                </div>);
        }
  }
}
