import React from 'react';
import './DummySubscription.css';
import { Subscription } from 'react-apollo';
import gql from 'graphql-tag';

const DUMMY_SUBSCRIPTION=gql`
subscription 
  dummySub($v:ID!){
     datum:dummyUpdates(roomId:$v){dateTime}
}`;
 
export default class DummySubscription extends React.Component {
    render(){
        return (
            <Subscription
              subscription={DUMMY_SUBSCRIPTION}
              variables={{v:4}}
            >
              {({data,loading,error})=>(
                  <div>
                    <label>
                      {error && "there was an error" }
                      {loading && "loading!"}
                      {!loading && !error && data.datum.dateTime}
                  </label>
                  </div>
              )}
            </Subscription>
        );
    }
}
