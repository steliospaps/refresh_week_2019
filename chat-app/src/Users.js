import React, { Component } from 'react';
import { Query } from 'react-apollo';
import gql from 'graphql-tag';
import User from './User';

export default class Users extends Component {
    render() {
        return (
            <Query query={gql`query {users:getAllUsers{id,name}}`} >
              {({ loading, error, data }) => {
                  if (loading) return <div>Fetching</div>;
                  if (error) return <div>Error</div>;
                  
                  const usersToRender = data.users;
                  
                  return (
                      <div>{usersToRender.map(user => <User key={user.id} user={user} />)}</div>
                  );
              }}
            </Query>
        );
  }
}
