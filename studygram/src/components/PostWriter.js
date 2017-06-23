import React, {Component, PropTypes} from 'react';
import ReactDOM from 'react-dom';
import next from '../img/next.svg'
import Slider from './Slider';
import Axios from 'axios';
import MarkdownEditor from 'react-markdown-editor';
import { Config } from '../resource';

const MarkdownEditorComponent = MarkdownEditor.MarkdownEditor;

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
        };

        this.onTextInputChange = this.onTextInputChange.bind(this);
        this.sendPost = this.sendPost.bind(this);
    }

    sendPost() {
        let parsedTag = this.state.tagStr.split(" ");
        let tagArr = [];
        parsedTag.forEach((data) => {
            tagArr.push(data);
        });
        let sendData = new FormData();
        sendData.append('title', this.state.title);
        sendData.append('content', this.state.content);
        sendData.append('tags', JSON.stringify(tagArr));
        sendData.append('image', this.fileInput.files[0]);

        console.log(this.fileInput.files[0]);
        Axios({
            method: 'post',
            url: `${ Config.ip }/api/gram`,
            data: sendData
        });
    }

    onTextInputChange(e) {
        let nextState = {};
        nextState[e.target.name] = e.target.value;
        console.log(e.target.name);
        this.setState(nextState);
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
                        <input className="Title-Input" type="text" placeholder="제목을 입력하세요." name="title" value={this.state.title} onChange={this.onTextInputChange}></input>
                    </div>
                    <div className="Slide">
                        <div className="Title-Input-Div">
                            <label className="Write-Content-Label"></label>
                            <p>무엇을 알게 되었나요?</p>
                        </div>
                    </div>
                    <div className="Slide">
                        <div className="Title-Input-Div">
                            <label className="Write-Content-Label" type="text"/>
                            <p>태그를 달아주세요!</p>
                        </div>
                        <br></br>
                        <input className="Title-Input" type="text" placeholder="#js #react #css" name="tagStr" value={this.state.tagStr} onChange={this.onTextInputChange}></input>
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
