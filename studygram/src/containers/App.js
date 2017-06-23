import React, {Component} from 'react';
import Axios from 'axios';
/* import logo from './logo.svg'; */
/* import './App.css'; */
import {Header, Card, Contents, PostWriter} from '../components';
import FacebookLogin from 'react-facebook-login';
import {Config} from '../resource';
class App extends Component {
    constructor(props) {
        super(props);
        this.step = 12;
        this.state = {
            gramList: [],
            lastIndex: 0,
            isGettingGram: false
        }
        this.getGrams = this.getGrams.bind(this);
    }
    getGrams() {
        this.setState({isGettingGram: true});
        Axios({url: `${Config.ip}/api/gram/${this.state.lastIndex}`, method: 'get'}).then((response) => {
            response.data.forEach((data, index) => {
                response.data[index] = JSON.parse(data);
            })
            this.setState({
                gramList: [
                    ...this.state.gramList,
                    ...response.data
                ],
                lastIndex: this.state.lastIndex + this.step,
                isGettingGram: false
            })
        }).catch((error) => {
            console.error(error);
        });
    }
    componentDidMount() {
        window.addEventListener("scroll", () => {
            let topScrollBottom = window.pageYOffset + window.innerHeight;
            if (document.body.clientHeight <= topScrollBottom) {
                console.info("asdfasdf222 " + this.state.isGettingGram);
                if (!this.state.isGettingGram) {
                    this.getGrams();
                    console.info("asdfasdf");
                }
                return;
            } else if (this.isGettingGram) {
                return;
            }
        });
    }
    setCookie(cname, cvalue, exdays) {
        let d = new Date();
        d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
        var expires = "expires=" + d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + "; domain=.naver.com;";
    }
    render() {
        const convertToCard = (cardArr) => {
            return cardArr.map((cardData, i) => {
                return (<Card key={i} time={cardData.time} picture={`${Config.ip}/api/image/${cardData.files}`} name={cardData.name} title={cardData.title} time={cardData.time} content={cardData.content} tags={cardData.tags} cardId={cardData['_id']}/>)
            });
        }
        return (
            <div className="App">
                <Header></Header>
                <Contents>
                    <PostWriter></PostWriter>
                    {convertToCard(this.state.gramList)}
                    <FacebookLogin appId={Config.fbAppId} autoLoad={true} fields="name,email,picture" onClick={() => {}} callback={(response) => {
                        if (response.accessToken) {
                            console.info("success callblack");
                            Axios({
                                method: "post",
                                url: `${Config.ip}/api/login`,
                                data: {
                                    accessToken: response.accessToken
                                }
                            }).then((response) => {
                                this.getGrams();
                            }).catch((error) => {
                                console.log(error);
                            });
                        }
                    }}/>
                </Contents>
            </div>
        );
    }
}
export default App;
