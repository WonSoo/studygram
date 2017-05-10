import React, {Component, PropTypes} from 'react';
import ReactDOM from 'react-dom';
import next from '../img/next.svg'
import Slider from './Slider';
import Axios from 'axios';
import { Config } from '../resource';

const propTypes = {};
const defaultProps = {};
class PostWriter extends Component {
    constructor(props) {
        super(props);
        this.fileInput;
        this.state = {
            title: '',
            content: '',
            tagStr: '',
            tags: [],
            selectPicture: {}
        };

        this.onTitleChange = this.onTitleChange.bind(this);
        this.onContentChange = this.onContentChange.bind(this);
        this.onTagChange = this.onTagChange.bind(this);
        this.sendPost = this.sendPost.bind(this);
    }

    sendPost() {
        console.log(this.state.tagStr);
        let sendData = new FormData();
        sendData.append('title', this.state.title);
        sendData.append('content', this.state.content);
        sendData.append('tags', this.state.tags);
        sendData.append('image', this.fileInput.getInputDOMNode().files[0]);
        console.log(this.fileInput.getInputDOMNode().files[0]);
        Axios({
            method: 'post',
            url: `${ Config.ip }/api/gram`,
            data: sendData
        });
    }

    onContentChange(e) {
        this.setState({
            content: e.target.value
        })
    }

    onTitleChange(e) {
        this.setState({
            title: e.target.value
        })
    }

    onTagChange(e) {
        let parsedTag = e.target.value.split(" ");
        let tagArr = [];
        parsedTag.forEach((data) => {
            tagArr.push(data);
        });
        console.log(tagArr);
        this.setState({
            tagStr: e.target.value,
            tags: tagArr
        })
    }

    render() {
        return (
            <div className="Card Post-Writer">
                <Slider showArrow={true}>
                    <div className="Slide">
                        <div className="Title-Input-Div">
                            <label className="Write-Content-Label"></label>
                            <p>강렬한 제목을 입력하세요.</p>
                        </div>
                        <input className="Title-Input" type="text" placeholder="제목을 입력하세요." value={this.state.title} onChange={this.onTitleChange}></input>
                    </div>
                    <div className="Slide">
                        <div className="Title-Input-Div">
                            <label className="Write-Content-Label"></label>
                            <p>무엇을 알게 되었나요?</p>
                        </div>
                        <textarea className="Title-Input" value={this.state.content} onChange={this.onContentChange}></textarea>
                    </div>
                    <div className="Slide">
                        <div className="Title-Input-Div">
                            <label className="Write-Content-Label" type="text"/>
                            <p>태그를 달아주세요!</p>
                        </div>
                        <br></br>
                        <input className="Title-Input" type="text" placeholder="#js #react #css"  value={this.state.tagStr} onChange={this.onTagChange}></input>
                        <input className="Title-Input" type="file" ref={(ref) => { this.fileInput = ref }}></input>
                        <input type="button" placeholder="post" value={'전송'} onClick={ this.sendPost }></input>
                    </div>
                </Slider>
            </div>
        );
    }
}
PostWriter.propTypes = propTypes;
PostWriter.defaultProps = defaultProps;
export default PostWriter;
