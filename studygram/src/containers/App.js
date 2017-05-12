import React, {Component} from 'react';
import Axios from 'axios';

/* import logo from './logo.svg'; */
/* import './App.css'; */
import {Header, Card, Contents, PostWriter} from '../components';
import FacebookLogin from 'react-facebook-login';
import { Config } from '../resource';

class App extends Component {
    constructor(props) {
        super(props);
        this.step = 12;
        this.state = {
            gramList: [{title: 'title',
                        time: '1분전',
                        content: '나는 아니야',
                        tags: ['momo', 'twice', 'gram']},
                        {
                        title: 'title',
                        time: '1분전',
                        content: '나는 아니야',
                        tags: ['momo', 'twice', 'gram']},
                        {
                        title: 'title',
                        time: '1분전',
                        content: '나는 아니야',
                        tags: ['momo', 'twice', 'gram']},
                        {
                        title: 'title',
                        time: '1분전',
                        content: '나는 아니야',
                        tags: ['momo', 'twice', 'gram']},
                        {
                        title: 'title',
                        time: '1분전',
                        content: '나는 아니야',
                        tags: ['momo', 'twice', 'gram']}],
            lastIndex: 0
        }

        this.getGrams = this.getGrams.bind(this);
    }

    getGrams() {
        Axios({
            url: `${ Config.ip }/api/gram/${ this.state.lastIndex }`,
            method: 'get'
        }).then((response) => {
            this.setState({
                gramList: [...this.state.gramList, ...response],
                lastIndex: this.state.lastIndex + this.step
            })
        }).catch((error) => {
            console.error(error);
        });
    }

    componentWillMount() {
        this.getGrams();
    }

    setCookie(cname, cvalue, exdays) {
        let d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+ d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }

    render() {
      console.log(`${Config.ip}`);
        const convertToCard = (cardArr) => {
            return cardArr.map((cardData, i) => {
                return (<Card key={i} name={cardData.name} title={cardData.title} time={cardData.time} content={cardData.content} tags={cardData.tags}/>)
            });
        }
        return (
            <div className="App">
                <Header></Header>
                <Contents>
                      <PostWriter></PostWriter>
                      {convertToCard(this.state.gramList)}
                      <FacebookLogin appId={ Config.fbAppId } autoLoad={true} fields="name,email,picture" onClick={() => {
                      }} callback={(response) => {
                          this.setCookie('accessToken', response.accessToken, 1);
                          console.log(response);
                          if(response.accessToken) {
                              Axios.post(`${ Config.ip }/api/login`).then((response) => {
                                  console.log(response);
                              }).catch((error) => {
                                  console.log(error);
                              });
                          }
                      //    console.log(response.status);
                      }}/>
                </Contents>
            </div>
        );
    }
}

export default App;
